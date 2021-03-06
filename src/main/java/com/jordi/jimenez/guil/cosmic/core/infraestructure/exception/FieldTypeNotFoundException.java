package com.jordi.jimenez.guil.cosmic.core.infraestructure.exception;

public class FieldTypeNotFoundException extends RuntimeException {
  public FieldTypeNotFoundException(String value) {
    super(String.format("The field type [%s] not found.", value));
  }
}
