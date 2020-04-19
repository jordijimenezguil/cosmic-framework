package com.jordi.jimenez.guil.cosmic.core.domain.businessDomain;

import reactor.core.publisher.Mono;

public interface BusinessDomainRepository {

  Mono<Boolean> create(BusinessDomain businessDomain);

}
