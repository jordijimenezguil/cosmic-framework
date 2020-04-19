package com.jordi.jimenez.guil.cosmic.core.exception;

public class BusinessDomainParseErrorException extends RuntimeException {

  public BusinessDomainParseErrorException(String domainName, String reason) {
    super(String.format("Trying to parse Business Domain [%s] occurs some error. Reason: %s", domainName, reason));
  }
}
