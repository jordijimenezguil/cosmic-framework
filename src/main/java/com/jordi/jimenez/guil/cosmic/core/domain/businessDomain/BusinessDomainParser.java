package com.jordi.jimenez.guil.cosmic.core.domain.businessDomain;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;
import com.jordi.jimenez.guil.cosmic.core.exception.BusinessDomainParseErrorException;

import java.util.LinkedHashMap;
import java.util.Map;

public interface BusinessDomainParser {


  default BusinessDomain parse(Map<String, Object> domainData, DomainMetaModel domainMetaModel) {
    if (domainData == null || domainData.isEmpty()) {
      throw new BusinessDomainParseErrorException(domainMetaModel.getName(), "Domain body is empty");
    }
    if (dataNotContainsRequiredFields(domainData, domainMetaModel)) {
      throw new BusinessDomainParseErrorException(domainMetaModel.getName(), "Required fields not presents.");
    }

    Map<String, Object> validatedData = new LinkedHashMap<>();

    domainMetaModel.getAllFields()
        .forEach(field -> {
          Object value = domainData.get(field.getName());
          if (value != null) {
            validatedData.put(field.getName(), value);
          }
        });

    return new BusinessDomain(domainMetaModel, validatedData);

  }


  default boolean dataNotContainsRequiredFields(Map<String, Object> domainData, DomainMetaModel domainMetaModel) {
    return domainMetaModel.getAllFields().stream()
        .anyMatch(field -> !field.getUniqueIdentifier()
            && field.getRequired()
            && !domainData.containsKey(field.getName()));
  }
}
