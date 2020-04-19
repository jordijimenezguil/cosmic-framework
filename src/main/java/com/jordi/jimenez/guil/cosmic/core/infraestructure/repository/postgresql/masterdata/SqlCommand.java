package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata;

import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.SPACE;

public class SqlCommand {
  public static final String CREATE_SCHEMA = "CREATE SCHEMA" + SPACE;
  public static final String DROP_SCHEMA = "DROP SCHEMA" + SPACE;
  public static final String CREATE_TABLE = "CREATE_TABLE" + SPACE;
  public static final String TRUNCATE = "TRUNCATE TABLE" + SPACE;
  public static final String INSERT_INTO = "INSERT INTO" + SPACE;
  public static final String SELECT = "SELECT" + SPACE;
  public static final String FROM = "FROM" + SPACE;
  public static final String WHERE = "WHERE" + SPACE;
  public static final String DELETE_FROM = "DELETE FROM" + SPACE;


}
