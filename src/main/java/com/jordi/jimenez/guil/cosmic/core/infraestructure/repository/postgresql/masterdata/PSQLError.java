package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata;

public enum PSQLError {

  DUPLICATE_SCHEMA("42P06", "Schema exists."),
  DUPLICATE_TABLE("42P07", "Table  exists.");

  public String error;
  public String description;


  PSQLError(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
