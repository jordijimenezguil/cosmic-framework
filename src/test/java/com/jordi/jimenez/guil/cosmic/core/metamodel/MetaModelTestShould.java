package com.jordi.jimenez.guil.cosmic.core.metamodel;

import org.junit.jupiter.api.Test;

import static com.jordi.jimenez.guil.cosmic.core.metamodel.FieldType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.metamodel.FieldType.STRING;
import static org.assertj.core.api.Assertions.assertThat;

class MetaModelTestShould {


  @Test
  void return_full_meta_model() {
    MetaModel metaModel = MetaModel.builder()
        .withDomain("user")
        .withUniqueIdentifierField("id", "ID", INTEGER)
        .withField("name", "NAME", STRING)
        .end()

        .withDomain("article")
        .withUniqueIdentifierField("id", "ID", INTEGER)
        .withField("title", "TITLE", STRING)
        .withField("description", "DESC", STRING)
        .end()

        .build();

    assertThat(metaModel)
        .isNotNull();
    assertThat(metaModel.getDomains()).hasSize(2);

  }
}