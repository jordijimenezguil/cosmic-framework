package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;


import com.jordi.jimenez.guil.cosmic.core.infraestructure.service.system.FileService;

public class QueryContainer {
  public String QUERY_CREATE_SCHEMA;
  public String QUERY_DELETE_SCHEMA;
  public String QUERY_DROP_DATABASE;


  private final static String CREATE_TABLE = "CREATE TABLE ";


  public QueryContainer(FileService fileService) {
    QUERY_CREATE_SCHEMA = fileService.fileAsString("cosmic/db/queries/create_schema.sql");
    //QUERY_DELETE_SCHEMA = fileService.fileAsString("cosmic/db/queries/user/get_user_by_name.sql");
    //QUERY_DROP_DATABASE = fileService.fileAsString("cosmic/db/queries/user/get_user_by_login.sql");
  }





/*

  CREATE TABLE user_app (
      id VARCHAR(64) PRIMARY KEY,
      name VARCHAR(50),
      password VARCHAR(80),
      status INT,
      role VARCHAR(20),
      creation_moment timestamp,
      modification_moment timestamp
);

  CREATE INDEX index_user_app_name_password
  ON user_app (name, password);*/


}
