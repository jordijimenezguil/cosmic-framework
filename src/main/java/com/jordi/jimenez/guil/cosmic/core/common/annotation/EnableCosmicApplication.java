package com.jordi.jimenez.guil.cosmic.core.common.annotation;

import com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration.CosmicConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@EnableWebSecurity
@SpringBootApplication(scanBasePackages = {"com.jordi.jimenez.guil"})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CosmicConfiguration.class)
public @interface EnableCosmicApplication {

}
