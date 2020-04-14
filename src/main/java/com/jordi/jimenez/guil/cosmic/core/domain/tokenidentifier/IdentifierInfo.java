package com.jordi.jimenez.guil.cosmic.core.domain.tokenidentifier;


public class IdentifierInfo {

  private UserRole userRole;

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }


  public static final class IdentifierInfoBuilder {
    private UserRole userRole;

    private IdentifierInfoBuilder() {
    }

    public static IdentifierInfoBuilder anIdentifierInfo() {
      return new IdentifierInfoBuilder();
    }

    public IdentifierInfoBuilder withRole(UserRole userRole) {
      this.userRole = userRole;
      return this;
    }

    public IdentifierInfo build() {
      IdentifierInfo identifierInfo = new IdentifierInfo();
      identifierInfo.setUserRole(userRole);
      return identifierInfo;
    }
  }
}
