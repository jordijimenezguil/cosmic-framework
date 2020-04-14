package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelable;
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
    CosmicMetalModelable cosmicMetalModelable = context.getBean(CosmicMetalModelable.class);
    CosmicUseCase cosmicUseCase = context.getBean(CosmicUseCase.class);

    cosmicUseCase.start(cosmicMetalModelable.getMetalModel());
  }

}
