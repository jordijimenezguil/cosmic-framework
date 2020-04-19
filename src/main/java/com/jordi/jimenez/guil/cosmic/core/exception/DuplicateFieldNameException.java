package com.jordi.jimenez.guil.cosmic.core.exception;

public class DuplicateFieldNameException extends RuntimeException {
  public DuplicateFieldNameException(String name) {
    super(String.format("The field name [%s] is duplicated.", name));
  }
}