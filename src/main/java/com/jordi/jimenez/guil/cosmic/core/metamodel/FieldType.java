package com.jordi.jimenez.guil.cosmic.core.metamodel;

import com.jordi.jimenez.guil.cosmic.core.exception.FieldTypeNotFoundException;

import java.util.Arrays;

public enum FieldType {

  BYTE("Byte"),
  SHORT("Short"),
  INTEGER("Integer"),
  LONG("Long"),
  FLOAT("Floar"),
  DOUBLE("Double"),
  STRING("String"),
  CHARACTER("Character"),
  BOOLEAN("Boolean");
  public String type;

  FieldType(String type) {
    this.type = type;
  }

  public FieldType getFieldTypeByValue(String value) {
    return Arrays.stream(FieldType.values())
        .filter(fieldType -> fieldType.type.equals(value))
        .findFirst()
        .orElseThrow(() -> new FieldTypeNotFoundException(value));
  }
}
