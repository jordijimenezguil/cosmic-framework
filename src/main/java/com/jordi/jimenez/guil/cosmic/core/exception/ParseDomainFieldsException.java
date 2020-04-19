package com.jordi.jimenez.guil.cosmic.core.exception;

public class ParseDomainFieldsException extends RuntimeException {

  public ParseDomainFieldsException(String domainName) {
    super(String.format("Parse error occurs while mounting query fields of domain [%s]", domainName));
  }
}
