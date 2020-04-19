package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory;

import com.jordi.jimenez.guil.cosmic.core.TestHelper;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BusinessDomainQueryFactoryTestShould {


  @Test
  void return_insert_query() {
    BusinessDomain dummyBusinessDomain = TestHelper.createDummyBusinessDomain();

    String insert = BusinessDomainQueryFactory.createInsert(dummyBusinessDomain);
    Assertions.assertThat(insert)
        .isNotNull()
        .isEqualTo("");
  }
}