package com.jordi.jimenez.guil.cosmic.core.domain.businessDomain;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;

import java.util.Map;

public class BusinessDomainFactory {

  private BusinessDomainParser businessDomainParser;

  public BusinessDomainFactory(BusinessDomainParser businessDomainParser) {
    this.businessDomainParser = businessDomainParser;
  }

  public BusinessDomain build(Map<String, Object> domainData, DomainMetaModel domainMetaModel) {
    return businessDomainParser.parse(domainData, domainMetaModel);
  }
}
