package com.example.bands_service_3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.wildfly.naming.client.WildFlyInitialContextFactory;
import service.CoordService;
import service.impl.CoordServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class EjbConfiguration {

    @Bean
    public Context context() throws NamingException {
        var jndiProps = new Properties();
        jndiProps.setProperty(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
        jndiProps.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProps.setProperty("jboss.naming.client.ejb.context", "true");
        jndiProps.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        return new InitialContext(jndiProps);
    }

    @Bean
    @DependsOn("context")
    public CoordService coordServiceBean() throws NamingException {
        return (CoordService) context().lookup(this.getFullName(CoordServiceImpl.class, CoordService.class));
    }

    private String getFullName(Class<?> clazz, Class<?> intType) {
        var moduleName = "ejb:/bands_ejb-0.0.2.jar";
        var beanName = clazz.getSimpleName();
        var viewClassName = intType.getName();
        return "%s/%s!%s".formatted(moduleName, beanName, viewClassName);
    }

}
