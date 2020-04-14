package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.common.checker.KeyWordChecker;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.service.checker.PostgreSQLKeyWordChecker;
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


  @Bean
  public KeyWordChecker getKeyWordChecker() {
    return new PostgreSQLKeyWordChecker();
  }
}
