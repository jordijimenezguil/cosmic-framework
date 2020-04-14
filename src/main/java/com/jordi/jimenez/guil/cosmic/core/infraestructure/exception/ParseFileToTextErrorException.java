package com.jordi.jimenez.guil.cosmic.core.infraestructure.exception;

public class ParseFileToTextErrorException extends RuntimeException {

  public ParseFileToTextErrorException(Throwable e) {
    super("Occurs Error while parse file to text from file system.", e);
  }

}
