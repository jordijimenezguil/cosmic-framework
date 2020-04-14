package com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier;


import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.UserRoleNotFoundException;

import java.util.Arrays;

public enum UserRole {

  ADMINISTRATOR("ADMIN_ROLE"),
  USER("USER_ROLE"),

  ;

  public final String role;


  UserRole(String role) {
    this.role = role;
  }


  public static UserRole getRoleByValue(String value) {
    return Arrays.stream(UserRole.values())
        .filter(r -> r.role.equals(value))
        .findFirst()
        .orElseThrow(() -> new UserRoleNotFoundException(value));

  }


  public static boolean exists(String value) {
    return Arrays.stream(UserRole.values())
        .anyMatch(r -> r.role.equals(value));

  }
}
