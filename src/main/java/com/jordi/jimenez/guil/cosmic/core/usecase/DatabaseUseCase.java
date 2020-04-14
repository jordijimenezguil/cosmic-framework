package com.jordi.jimenez.guil.cosmic.core.usecase;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DatabaseUseCase {

  private InfraDatabaseRepository infraDatabaseRepository;

  public DatabaseUseCase(InfraDatabaseRepository infraDatabaseRepository) {
    this.infraDatabaseRepository = infraDatabaseRepository;
  }

  public Mono<Boolean> start(MetaModel metaModel) {
    return Mono.zip(
        createControlSchemaIfNotExists(),
        createControlTableIfNotExists())
        .flatMap(t -> t.getT1() == 1 && t.getT2() == 1 ? createDomains(metaModel) : Mono.empty());
  }


  private Mono<Integer> createControlSchemaIfNotExists() {
    return infraDatabaseRepository.createControlSchemaIfNotExists();
  }


  private Mono<Integer> createControlTableIfNotExists() {
    return infraDatabaseRepository.createControlTable();
  }


  public Mono<Boolean> createDomains(MetaModel metaModel) {
    return Flux.fromIterable(metaModel.getDomains().values())
        .map(infraDatabaseRepository::createDomainTable)
        .collectList()
        .map(l -> Boolean.TRUE);
  }
}
