package br.com.rbarbioni.store.controller;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by renan on 15/12/15.
 */
@RestController
@RequestMapping(value="auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private String USERNAME = "master";

    private String PASSWORD = "master";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String doAuthorization ( @RequestParam String username, @RequestParam String password, HttpServletResponse response ){

        if ( !USERNAME.equals(username) && PASSWORD.equals(password) ){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        return  Jwts.builder().setSubject( username ).compact();
    }
}
