package com.jordi.jimenez.guil.cosmic.core;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.BOOLEAN;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.FLOAT;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.LONG;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.STRING;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.TIMESTAMP;

public class TestHelper {

  public static final String DOMAIN_NAME_1 = "client";
  public static final String DOMAIN_NAME_2 = "article";

  public static MetaModel createDummyMetaModel() {
    return MetaModel.builder()
        .withDomain(DOMAIN_NAME_1)
        .withUniqueIdentifierField("id", "id")
        .withRequiredField("name", "name", STRING)
        .withRequiredField("isEnabled", "is_enable", BOOLEAN)
        .withField("accountNumber", "account_number", LONG)
        .withField("discount", "discount", FLOAT)
        .withField("creationDate", "creation_date", TIMESTAMP)
        .end()

        .withDomain(DOMAIN_NAME_2)
        .withUniqueIdentifierField("id", "id")
        .withField("title", "title", STRING)
        .withField("description", "desc", STRING)

        .end()

        .build();
  }


  public static BusinessDomain createDummyBusinessDomain() {
    Map<String, Object> domainData = new HashMap<>();

    domainData.put("id", "72626");
    domainData.put("name", "John");
    domainData.put("isEnabled", true);
    domainData.put("accountNumber", 7575656757657L);
    domainData.put("discount", 2.5);
    domainData.put("creationDate", new Date());


    return new BusinessDomain(createDummyMetaModel().getDomains().get(DOMAIN_NAME_1), domainData);
  }
}
