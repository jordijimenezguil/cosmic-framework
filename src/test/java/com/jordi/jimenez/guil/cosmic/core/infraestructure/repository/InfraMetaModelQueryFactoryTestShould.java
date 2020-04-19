package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;

import com.jordi.jimenez.guil.cosmic.core.TestHelper;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory.InfraMetaModelQueryFactory;
import org.junit.jupiter.api.Test;

import static com.jordi.jimenez.guil.cosmic.core.TestHelper.DOMAIN_NAME_1;
import static org.assertj.core.api.Assertions.assertThat;


class InfraMetaModelQueryFactoryTestShould {


  @Test
  void return_create_table_query_from_domain() {
    MetaModel metaModel = TestHelper.createDummyMetaModel();

    String createTableSqlScript = InfraMetaModelQueryFactory
        .createDomainTable(metaModel
            .getDomains()
            .get(DOMAIN_NAME_1));

    assertThat(createTableSqlScript)
        .isNotNull()
        .isEqualTo("");
  }


}