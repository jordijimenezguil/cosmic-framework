package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelContext;
import com.jordi.jimenez.guil.cosmic.core.usecase.CosmicUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class CosmicConfiguration {

  @Autowired
  private ApplicationContext context;

  @EventListener(ApplicationReadyEvent.class)
  public void startCosmicApplication() {
    CosmicMetalModelContext cosmicMetalModelContext = context.getBean(CosmicMetalModelContext.class);
    CosmicUseCase cosmicUseCase = context.getBean(CosmicUseCase.class);

    cosmicUseCase.start(cosmicMetalModelContext.getMetalModel());
  }

}
