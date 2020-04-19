package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.common.checker.KeyWordChecker;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.usecase.CosmicUseCase;
import com.jordi.jimenez.guil.cosmic.core.usecase.InfraDatabaseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {


  @Bean
  public InfraDatabaseUseCase getDatabaseUseCase(InfraDatabaseRepository infraDatabaseRepository) {
    return new InfraDatabaseUseCase(infraDatabaseRepository);
  }

  @Bean
  public CosmicUseCase getCosmicUseCase(InfraDatabaseUseCase infraDatabaseUseCase, KeyWordChecker keyWordChecker) {
    return new CosmicUseCase(infraDatabaseUseCase, keyWordChecker);
  }

}
