package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.infraestructure.service.system.FileService;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.service.system.JsonTextFileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public FileService getJsonTextFileService() {
    return new JsonTextFileService();
  }
}
