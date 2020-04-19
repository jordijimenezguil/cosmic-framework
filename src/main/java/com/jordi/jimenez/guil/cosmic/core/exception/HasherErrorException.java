package com.jordi.jimenez.guil.cosmic.core.exception;

public class HasherErrorException extends RuntimeException {

  public HasherErrorException(Throwable e) {
    super("Occurs error while hasher creating hash.", e);
  }

}
