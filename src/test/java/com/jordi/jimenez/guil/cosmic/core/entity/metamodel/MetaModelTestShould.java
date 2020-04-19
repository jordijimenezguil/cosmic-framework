package com.jordi.jimenez.guil.cosmic.core.entity.metamodel;

import com.jordi.jimenez.guil.cosmic.core.TestHelper;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.exception.DuplicateDomainNameException;
import com.jordi.jimenez.guil.cosmic.core.exception.DuplicateFieldNameException;
import org.junit.jupiter.api.Test;

import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType.STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MetaModelTestShould {


  @Test
  void return_full_meta_model() {
    MetaModel metaModel = TestHelper.createDummyMetaModel();

    assertThat(metaModel)
        .isNotNull();
    assertThat(metaModel.getDomains())
        .hasSize(2);
  }

  @Test
  void throw_duplicate_domain_name_exception() {
    assertThrows(DuplicateDomainNameException.class,
        () -> MetaModel.builder()
            .withDomain("user")
            .withUniqueIdentifierField("id", "ID")
            .withField("name", "NAME", STRING)
            .end()

            .withDomain("user")
            .end()

            .build());
  }

  @Test
  void throw_duplicate_field_name_exception() {
    assertThrows(DuplicateFieldNameException.class,
        () -> MetaModel.builder()
            .withDomain("user")
            .withUniqueIdentifierField("id", "ID")
            .withField("id", "NAME", STRING)
            .end()

            .build());
  }
}