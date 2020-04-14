package com.jordi.jimenez.guil.cosmic.core.infraestructure.exception;

public class DuplicateDomainNameException extends RuntimeException {
  public DuplicateDomainNameException(String name) {
    super(String.format("The domain name [%s] is duplicated.", name));
  }
}