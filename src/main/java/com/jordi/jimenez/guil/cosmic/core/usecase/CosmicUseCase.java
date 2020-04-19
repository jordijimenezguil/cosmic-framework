package com.jordi.jimenez.guil.cosmic.core.usecase;

import com.jordi.jimenez.guil.cosmic.core.common.checker.KeyWordChecker;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.exception.KeyWordNotAllowDomainNameException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.TRUE;

public class CosmicUseCase {

  private InfraDatabaseUseCase infraDatabaseUseCase;
  private KeyWordChecker keyWordChecker;

  public CosmicUseCase(InfraDatabaseUseCase infraDatabaseUseCase, KeyWordChecker keyWordChecker) {
    this.infraDatabaseUseCase = infraDatabaseUseCase;
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
        .doOnNext(b -> infraDatabaseUseCase.start(metaModel))
        .map(booleans -> TRUE)
        .block();
  }


  private void stopApplication() {
    System.exit(1);
  }

}
