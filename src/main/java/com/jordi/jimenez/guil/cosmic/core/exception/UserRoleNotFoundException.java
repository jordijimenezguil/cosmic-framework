package com.jordi.jimenez.guil.cosmic.core.exception;

public class UserRoleNotFoundException extends RuntimeException {

  public UserRoleNotFoundException(String value) {
    super(String.format("User role[%s] not found.", value));
  }
}
