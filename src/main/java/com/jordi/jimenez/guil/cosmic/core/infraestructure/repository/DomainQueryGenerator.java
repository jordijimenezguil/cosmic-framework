package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainField;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;

public class DomainQueryGenerator {
  private final static String SPACE = " ";
  private final static String EMPTY = "";
  private final static String COMMA = ",";
  private final static String ENTER = "\n";
  private final static String SUFFIX_SCHEMA = "_schema";
  private final static String SCHEMA_CONTROL = "cosmic_control" + SUFFIX_SCHEMA;
  private final static String TABLE_CONTROL = "control";
  private final static String IDENTIFIER_VERSION_CONTROL = "9999";

  public String mountDomainSchemaName(DomainMetaModel domainMetaModel) {
    return domainMetaModel.getName() + SUFFIX_SCHEMA;
  }

  public String createDomainSchema(DomainMetaModel domainMetaModel) {
    return "CREATE SCHEMA " + mountDomainSchemaName(domainMetaModel);
  }

  public String createControlSchema() {
    return "CREATE SCHEMA " + SCHEMA_CONTROL;
  }

  public String dropDomainSchema(DomainMetaModel domainMetaModel) {
    return "DROP SCHEMA " + mountDomainSchemaName(domainMetaModel) +" CASCADE";
  }

  public String createDomainTable(DomainMetaModel domainMetaModel) {
    return "CREATE TABLE " + mountDomainSchemaName(domainMetaModel) + "." + domainMetaModel.getName() +
        "(" + ENTER +
        extractQuerySqlFields(domainMetaModel) +
        ")";
  }


  public String createControlTable() {
    return "CREATE TABLE " + SCHEMA_CONTROL + "." + TABLE_CONTROL + "(id INTEGER PRIMARY KEY, value TINYINT)";
  }

  public String insertVersionControl() {
    return "INSERT INTO " + SCHEMA_CONTROL + "." + TABLE_CONTROL + "("+IDENTIFIER_VERSION_CONTROL+", 1)";
  }


  public String deleteVersionControl() {
    return "DELETE FROM "  + SCHEMA_CONTROL + "." + TABLE_CONTROL + " WHERE ID="  + IDENTIFIER_VERSION_CONTROL;
  }
  private String extractQuerySqlFields(DomainMetaModel domainMetaModel) {
    StringBuilder sb = new StringBuilder();

    int counter = 0;
    for (DomainField domainField : domainMetaModel.getAllFields()) {
      boolean isLastElement = counter == domainMetaModel.getAllFields().size() - 1;
      sb.append(generateSQLParamField(domainField, isLastElement));
      sb.append(ENTER);
      counter++;
    }

    return sb.toString();
  }


  private String generateSQLParamField(DomainField domainField, boolean isLastField) {
    String primaryKey = domainField.getUniqueIdentifier() ? " PRIMARY KEY" : EMPTY;
    String endLineSeparator = isLastField ? EMPTY : COMMA;

    return domainField.getQueryName() +
        SPACE +
        domainField.getFieldType().sqlType +
        primaryKey +
        endLineSeparator;
  }
}
