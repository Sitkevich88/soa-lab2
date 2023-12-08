package ru.itmo.soa.config;

import beans.MusicBandEjb;
import interfaces.MusicBandBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.wildfly.naming.client.WildFlyInitialContextFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class EjbConfiguration {

    @Bean
    public Context context() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
        jndiProps.put(Context.PROVIDER_URL, "remote+http://localhost:8180");
        return new InitialContext(jndiProps);
    }

    @Bean
    @DependsOn("context")
    public MusicBandBean musicBandBean(Context context) throws NamingException {
        return (MusicBandBean) context.lookup(getFullName(MusicBandEjb.class, MusicBandBean.class));
    }

    private String getFullName(Class<?> classType, Class<?> interfaceType) {
        String moduleName = "ejb:/ejb-service";
        String beanName = classType.getSimpleName();
        String viewClassName = interfaceType.getName();
        return moduleName + "/" + beanName + "!" + viewClassName;
    }
}