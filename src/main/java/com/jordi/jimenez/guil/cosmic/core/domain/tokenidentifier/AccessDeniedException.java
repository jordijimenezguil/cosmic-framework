package com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier;

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException() {
    super("Access Denied to resource.");
  }

}
