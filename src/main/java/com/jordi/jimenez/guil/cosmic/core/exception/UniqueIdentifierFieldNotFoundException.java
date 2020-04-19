package com.jordi.jimenez.guil.cosmic.core.exception;

public class UniqueIdentifierFieldNotFoundException extends RuntimeException {
  public UniqueIdentifierFieldNotFoundException(String domainName) {
    super(String.format("The unique identifier field not found in domain [%s]", domainName));
  }
}
