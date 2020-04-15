package com.jordi.jimenez.guil.demobusinessapp;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetaModel;
import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelable;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;

import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.DATE;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.DOUBLE;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.FLOAT;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.LONG;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.STRING;


@CosmicMetaModel
public class MyBusinessMetalModel implements CosmicMetalModelable {


  @Override
  public MetaModel getMetalModel() {
    return MetaModel.builder()
        .withDomain("client")
        .withUniqueIdentifierField("id", "id", INTEGER)
        .withField("firstName", "first_name", STRING)
        .withField("lastName", "last_name", STRING)
        .withField("mobilePhone", "mobile_phone", STRING)
        .withField("fullAddress", "full_address", STRING)
        .withField("saleDiscount", "sale_discount", FLOAT)
        .withField("creationDate", "creation_date", DATE)
        .end()

        .withDomain("product")
        .withUniqueIdentifierField("reference", "reference", LONG)
        .withField("family", "family", STRING)
        .withField("title", "title", STRING)
        .withField("shortDescription", "short_desc", STRING)
        .withField("longDescription", "long_desc", STRING)
        .withField("retailPrice", "retail_price", DOUBLE)
        .withField("costPrice", "cost_price", DOUBLE)
        .end()


        .build();
  }
}
