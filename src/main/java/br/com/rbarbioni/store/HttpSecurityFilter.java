package br.com.rbarbioni.store;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;

/**
 * Created by renan on 15/12/15.
 */
@Order(value = 1)
@Component
public class HttpSecurityFilter implements Filter {

    public final String HEADER_SECURITY_KEY = "x-key";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (Arrays.asList(ApiWebApplicationContext.ALLOWED_PATH).contains(request.getPathInfo())){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Key secretKey = MacProvider.generateKey(SignatureAlgorithm.HS256);

        String key = servletRequest.getParameter("key");

        if ( key == null ){
            String[] keyHeader = servletRequest.getParameterMap().get(HEADER_SECURITY_KEY);
            if ( keyHeader != null && keyHeader.length > 0)
            key = keyHeader[0];
        }

        Jwts.parser().setSigningKey(secretKey).parse(key);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
