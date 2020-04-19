package com.jordi.jimenez.guil.demo.app;

import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetaModelContext;
import com.jordi.jimenez.guil.cosmic.core.common.annotation.CosmicMetalModelContext;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;

import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.DATE;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.DOUBLE;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.FLOAT;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.STRING;


@CosmicMetaModelContext
public class MyBusinessMetalModelContext implements CosmicMetalModelContext {


  @Override
  public MetaModel getMetalModel() {
    return MetaModel.builder()
        .withDomain("client")
        .withUniqueIdentifierField("id", "id")
        .withRequiredField("firstName", "first_name", STRING)
        .withField("lastName", "last_name", STRING)
        .withRequiredField("mobilePhone", "mobile_phone", STRING)
        .withField("fullAddress", "full_address", STRING)
        .withField("saleDiscount", "sale_discount", FLOAT)
        .withRequiredField("creationDate", "creation_date", DATE)
        .end()

        .withDomain("product")
        .withUniqueIdentifierField("reference", "reference")
        .withField("family", "family", STRING)
        .withField("title", "title", STRING)
        .withRequiredField("shortDescription", "short_desc", STRING)
        .withRequiredField("longDescription", "long_desc", STRING)
        .withField("retailPrice", "retail_price", DOUBLE)
        .withField("costPrice", "cost_price", DOUBLE)
        .end()


        .build();
  }
}
