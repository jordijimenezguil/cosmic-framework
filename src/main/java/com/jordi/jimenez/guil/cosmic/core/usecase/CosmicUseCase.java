package com.jordi.jimenez.guil.cosmic.core.usecase;

import com.jordi.jimenez.guil.cosmic.core.common.checker.KeyWordChecker;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.KeyWordNotAllowDomainNameException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.TRUE;

public class CosmicUseCase {

  private DatabaseUseCase databaseUseCase;
  private KeyWordChecker keyWordChecker;

  public CosmicUseCase(DatabaseUseCase databaseUseCase, KeyWordChecker keyWordChecker) {
    this.databaseUseCase = databaseUseCase;
    this.keyWordChecker = keyWordChecker;
  }

  public Boolean start(MetaModel metaModel) {
    return Flux.fromIterable(metaModel.getDomains().values())
        .flatMap(domainMetaModel -> keyWordChecker.isValid(domainMetaModel.getName())
            ? Mono.just(TRUE)
            : Mono.error(new KeyWordNotAllowDomainNameException(domainMetaModel.getName())))
        .doOnError(throwable -> {
          throwable.printStackTrace();
          stopApplication();
        })
        .collectList()
        .doOnNext(b -> databaseUseCase.start(metaModel))
        .map(booleans -> TRUE)
        .block();
  }


  private void stopApplication() {
    System.exit(1);
  }

}
