package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;

import com.jordi.jimenez.guil.cosmic.core.TestHelper;
import com.jordi.jimenez.guil.cosmic.core.entity.metamodel.MetaModel;
import org.junit.jupiter.api.Test;

import static com.jordi.jimenez.guil.cosmic.core.TestHelper.DOMAIN_NAME_1;
import static org.assertj.core.api.Assertions.assertThat;


class DomainQueryGeneratorTestShould {


  @Test
  void return_create_table_query_from_domain() {
    MetaModel metaModel = TestHelper.createDummyMetaModel();

    String createTableSqlScript = new DomainQueryGenerator(queryContainer)
        .createTable(metaModel
            .getDomains()
            .get(DOMAIN_NAME_1));

    assertThat(createTableSqlScript)
        .isNotNull()
        .isEqualTo("");
  }


}