package com.jordi.jimenez.guil.cosmic.core.domain.businessDomain;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;

import java.util.Map;

public class UpdateBusinessDomainParser implements BusinessDomainParser {

  @Override
  public boolean dataNotContainsRequiredFields(Map<String, Object> domainData, DomainMetaModel domainMetaModel) {
    return domainMetaModel.getAllFields().stream()
        .anyMatch(field -> field.getRequired()
            && !domainData.containsKey(field.getName()));

  }
}
