package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor.QuerySqlFieldExtractor;

import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor.FieldExtractorSetting.FieldExtractorSettingBuilder.aFieldExtractorSetting;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.SPACE;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.INSERT_INTO;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlOption.VALUES;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory.InfraMetaModelQueryFactory.concatSuffixSchemaToDomainName;

public class BusinessDomainQueryFactory {


  public static String createInsert(BusinessDomain businessDomain) {
    QuerySqlFieldExtractor nameExtractor = new QuerySqlFieldExtractor(aFieldExtractorSetting()
        .includeName()
        .build());
    QuerySqlFieldExtractor valueExtractor = new QuerySqlFieldExtractor(aFieldExtractorSetting()
        .includeValue()
        .build());

    return INSERT_INTO + concatSuffixSchemaToDomainName(businessDomain.getDomainMetaModel()) + SPACE
        + "(" + nameExtractor.extract(businessDomain.getDomainMetaModel()) + ")" + SPACE
        + VALUES + "(" + valueExtractor.extract(businessDomain) + ")";
  }

}
