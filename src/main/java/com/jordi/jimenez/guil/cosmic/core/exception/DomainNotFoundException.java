package com.jordi.jimenez.guil.cosmic.core.exception;

public class DomainNotFoundException extends RuntimeException {
  public DomainNotFoundException(String name) {
    super(String.format("The domain with name [%s] not found.", name));
  }
}
