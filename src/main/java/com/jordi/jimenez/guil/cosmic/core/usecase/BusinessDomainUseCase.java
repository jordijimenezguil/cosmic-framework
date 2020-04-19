package com.jordi.jimenez.guil.cosmic.core.usecase;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainRepository;
import reactor.core.publisher.Mono;

public class BusinessDomainUseCase {

  private BusinessDomainRepository businessDomainRepository;

  public BusinessDomainUseCase(BusinessDomainRepository businessDomainRepository) {
    this.businessDomainRepository = businessDomainRepository;
  }

  public Mono<Boolean> createElement(BusinessDomain businessDomain) {
    return businessDomainRepository.create(businessDomain);
  }

}
