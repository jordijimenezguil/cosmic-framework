package com.jordi.jimenez.guil.cosmic.core.infraestructure.controller;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelContext;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainFactory;
import com.jordi.jimenez.guil.cosmic.core.usecase.BusinessDomainUseCase;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;


@RestController
@RequestMapping(value = "/domain")
public class BusinessDomainController {

  private BusinessDomainUseCase businessDomainUseCase;
  private BusinessDomainFactory businessDomainFactory;
  private CosmicMetalModelContext cosmicMetalModelContext;

  public BusinessDomainController(BusinessDomainUseCase businessDomainUseCase,
                                  BusinessDomainFactory businessDomainFactory,
                                  CosmicMetalModelContext cosmicMetalModelContext) {
    this.businessDomainUseCase = businessDomainUseCase;
    this.businessDomainFactory = businessDomainFactory;
    this.cosmicMetalModelContext = cosmicMetalModelContext;
  }


  @PostMapping
  @RequestMapping(value = "/{domainName}", consumes = "application/json")
  public Mono<ResponseEntity> createElement(@PathVariable String domainName, @RequestBody Map<String, Object> domainData) {

    return Mono.just(cosmicMetalModelContext.getMetalModel())
        .map(metaModel -> metaModel.getDomainMetaModelByName(domainName))
        .map(domainMetaModel -> businessDomainFactory.build(domainData, domainMetaModel))
        .flatMap(businessDomainUseCase::createElement)
        .map(aBoolean -> aBoolean
            ? ResponseEntity.created(URI.create("")).build()
            : ResponseEntity.unprocessableEntity().build());
  }


}
