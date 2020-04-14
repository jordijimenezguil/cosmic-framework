package com.jordi.jimenez.guil.cosmic.core.infraestructure.exception;

public class DomainFieldNotFoundException extends RuntimeException {
  public DomainFieldNotFoundException(String name) {
    super(String.format("The field with [%s] not found.", name));
  }
}
