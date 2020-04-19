package com.jordi.jimenez.guil.cosmic.core.domain.businessDomain;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;

import java.util.Map;

public class BusinessDomain {

  private DomainMetaModel domainMetaModel;
  private Map<String, Object> domainData;

  public BusinessDomain(DomainMetaModel domainMetaModel, Map<String, Object> domainData) {
    this.domainMetaModel = domainMetaModel;
    this.domainData = domainData;
  }


  public DomainMetaModel getDomainMetaModel() {
    return domainMetaModel;
  }

  public Map<String, Object> getDomainData() {
    return domainData;
  }
}
