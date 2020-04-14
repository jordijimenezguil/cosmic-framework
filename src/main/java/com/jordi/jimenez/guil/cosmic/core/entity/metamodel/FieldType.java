package com.jordi.jimenez.guil.cosmic.core.entity.metamodel;

import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.FieldTypeNotFoundException;

import java.util.Arrays;

public enum FieldType {


  BOOLEAN("Boolean", "BIT"),
  BYTE("Byte", "TINYINT"),
  SHORT("Short", "SMALLINT"),
  INTEGER("Integer", "INTEGER"),
  LONG("Long", "BIGINT"),
  FLOAT("Float", "REAL"),
  DOUBLE("Double", "DOUBLE"),
  BIG_DECIMAL("BigDecimal", "NUMERIC"),

  STRING("String", "TEXT"),
  CHARACTER("Character", "TEXT"),

  DATE("Date", "DATE"),
  TIME("Time", "TIME"),
  TIMESTAMP("Timestamp", "TIMESTAMP"),

  ;


  public String javaType;
  public String sqlType;

  FieldType(String javaType, String sqlType) {
    this.javaType = javaType;
    this.sqlType = sqlType;
  }


  public FieldType getFieldTypeByValue(String value) {
    return Arrays.stream(FieldType.values())
        .filter(fieldType -> fieldType.javaType.equals(value))
        .findFirst()
        .orElseThrow(() -> new FieldTypeNotFoundException(value));
  }
}
