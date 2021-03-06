package com.jordi.jimenez.guil.cosmic.core.infraestructure.exception;

public class KeyWordNotAllowDomainNameException extends RuntimeException {
  public KeyWordNotAllowDomainNameException(String name) {
    super(String.format("The database keywords are not allowed for domain name: [%s]", name));
  }
}
