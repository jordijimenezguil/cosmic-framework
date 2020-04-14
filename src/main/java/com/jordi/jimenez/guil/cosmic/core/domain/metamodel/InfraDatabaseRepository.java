package com.jordi.jimenez.guil.cosmic.core.domain.metamodel;

import reactor.core.publisher.Mono;

public interface InfraDatabaseRepository {

  Mono<Integer> createDomainSchema(DomainMetaModel domainMetaModel);

  Mono<Integer> createDomainTable(DomainMetaModel domainMetaModel);

  Mono<Integer> createControlSchemaIfNotExists();

  Mono<Integer> createControlTable();

  Mono<Integer> updateVersionControl();

  Mono<Integer> clearDatabase();
}
