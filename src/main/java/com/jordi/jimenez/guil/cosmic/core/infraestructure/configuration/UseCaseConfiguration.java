package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.entity.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.usecase.DatabaseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {


  @Bean
  public DatabaseUseCase getDatabaseUseCase(InfraDatabaseRepository infraDatabaseRepository){
    return new DatabaseUseCase(infraDatabaseRepository);
  }
}
