package com.jordi.jimenez.guil.cosmic.core.infraestructure.service.checker;

import com.jordi.jimenez.guil.cosmic.core.common.checker.KeyWordChecker;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.PostgresqlKeyWords;

public class PostgreSQLKeyWordChecker implements KeyWordChecker {

  @Override
  public Boolean isValid(String domainName) {
    return !PostgresqlKeyWords.exists(domainName);
  }

}
