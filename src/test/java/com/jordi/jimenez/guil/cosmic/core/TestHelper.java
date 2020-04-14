package com.jordi.jimenez.guil.cosmic.core;

import com.jordi.jimenez.guil.cosmic.core.entity.metamodel.MetaModel;

import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.BOOLEAN;
import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.FLOAT;
import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.LONG;
import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.STRING;
import static com.jordi.jimenez.guil.cosmic.core.entity.metamodel.FieldType.TIMESTAMP;

public class TestHelper {

  public static final String DOMAIN_NAME_1 = "client";
  public static final String DOMAIN_NAME_2 = "article";

  public static MetaModel createDummyMetaModel() {
    return MetaModel.builder()
        .withDomain(DOMAIN_NAME_1)
        .withUniqueIdentifierField("id", "id", INTEGER)
        .withField("name", "name", STRING)
        .withField("isEnabled", "is_enable", BOOLEAN)
        .withField("accountNumber", "account_number", LONG)
        .withField("discount", "discount", FLOAT)
        .withField("creationDate", "creation_date", TIMESTAMP)
        .end()

        .withDomain(DOMAIN_NAME_2)
        .withUniqueIdentifierField("id", "id", INTEGER)
        .withField("title", "title", STRING)
        .withField("description", "desc", STRING)

        .end()

        .build();
  }
}
