package br.com.rbarbioni.store;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApiWebApplicationContext implements WebApplicationInitializer {
 
	private static ApplicationContext applicationContext;

    public static String REST_BASE_URI = "/api/*";

    public static String[] ALLOWED_PATH = {"/auth"};

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        context.getEnvironment().getActiveProfiles();
        dispatcher.addMapping(REST_BASE_URI);
        servletContext.addFilter(REST_BASE_URI, new HttpSecurityFilter()).addMappingForUrlPatterns(null, false, REST_BASE_URI);
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(Constants.PACKAGE_BASE);
        applicationContext = context;
        return context;
    }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
 
    
}
