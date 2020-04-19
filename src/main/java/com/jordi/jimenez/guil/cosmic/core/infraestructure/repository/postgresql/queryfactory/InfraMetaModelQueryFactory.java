package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor.QuerySqlFieldExtractor;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlDataType;

import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor.FieldExtractorSetting.FieldExtractorSettingBuilder.aFieldExtractorSetting;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.SPACE;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.CREATE_SCHEMA;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.CREATE_TABLE;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.DROP_SCHEMA;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.FROM;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.INSERT_INTO;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.SELECT;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlCommand.TRUNCATE;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlObjectName.NAME_FIELD_TABLE_CONTROL_VERSION;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlObjectName.NAME_SCHEMA_CONTROL;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlObjectName.NAME_SUFFIX_SCHEMA;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlObjectName.NAME_TABLE_CONTROL;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlOption.CASCADE;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlOption.LIMIT;

public class InfraMetaModelQueryFactory {


  public static String concatSuffixSchemaToDomainName(DomainMetaModel domainMetaModel) {
    return domainMetaModel.getName() + NAME_SUFFIX_SCHEMA;
  }

  public  static String createDomainSchema(DomainMetaModel domainMetaModel) {
    return CREATE_SCHEMA + concatSuffixSchemaToDomainName(domainMetaModel);
  }

  public  static String createControlSchema() {
    return CREATE_SCHEMA + NAME_SCHEMA_CONTROL;
  }

  public  static String dropDomainSchema(DomainMetaModel domainMetaModel) {
    return DROP_SCHEMA + concatSuffixSchemaToDomainName(domainMetaModel) + SPACE + CASCADE;
  }

  public  static String createDomainTable(DomainMetaModel domainMetaModel) {
    QuerySqlFieldExtractor fieldExtractor = new QuerySqlFieldExtractor(aFieldExtractorSetting()
        .includeName()
        .includeSqlType()
        .includeSqlPrimaryKey()
        .withColumnFormat()
        .build());
    return CREATE_TABLE + concatSuffixSchemaToDomainName(domainMetaModel) + "." + domainMetaModel.getName()
        + "(" + fieldExtractor.extract(domainMetaModel) + ")";
  }


  public  static String createControlTable() {
    return CREATE_TABLE + NAME_SCHEMA_CONTROL + "." + NAME_TABLE_CONTROL
        + "(" + NAME_FIELD_TABLE_CONTROL_VERSION + SPACE + SqlDataType.TEXT + ")";
  }

  public  static String insertVersionControl(MetaModel metaModel) {
    return INSERT_INTO + NAME_SCHEMA_CONTROL + "." + NAME_TABLE_CONTROL
        + "(" + metaModel.generateVersionHash() + ")";
  }

  public  static String readVersionControl() {
    return SELECT + NAME_FIELD_TABLE_CONTROL_VERSION
        + FROM + NAME_SCHEMA_CONTROL + "." + NAME_TABLE_CONTROL
        + LIMIT + SPACE + "1";
  }

  public static  String deleteVersionControl() {
    return TRUNCATE + NAME_SCHEMA_CONTROL + "." + NAME_TABLE_CONTROL;
  }


}
