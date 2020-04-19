package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.PSQLError;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory.InfraMetaModelQueryFactory;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

public class InfraDatabasePostgresqlRepository implements InfraDatabaseRepository {

  private final static Logger LOGGER = LoggerFactory.getLogger(InfraDatabasePostgresqlRepository.class);

  private final static Map<String, Object> EMPTY_MAP = new HashMap<>();
  private final static int RESULT_NOT_AFFECTED_ROWS = 0;
  private final static int RESULT_AFFECTED_ROWS = 1;


  private NamedParameterJdbcTemplate jdbcTemplate;

  public InfraDatabasePostgresqlRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public Mono<Integer> createDomainSchema(DomainMetaModel domainMetaModel) {
    return Mono.fromCallable(() -> jdbcTemplate
        .update(InfraMetaModelQueryFactory.createDomainSchema(domainMetaModel), EMPTY_MAP))
        .onErrorResume(throwable -> throwable instanceof BadSqlGrammarException
            ? checkIfExistsPSQLErrors(throwable)
            : Mono.just(RESULT_NOT_AFFECTED_ROWS));
  }


  @Override
  public Mono<Integer> createDomainTable(DomainMetaModel domainMetaModel) {
    return Mono.fromCallable(() -> jdbcTemplate
        .update(InfraMetaModelQueryFactory.createDomainTable(domainMetaModel), EMPTY_MAP))
        .onErrorResume(throwable -> throwable instanceof BadSqlGrammarException
            ? checkIfExistsPSQLErrors(throwable)
            : Mono.just(RESULT_NOT_AFFECTED_ROWS));
  }


  @Override
  public Mono<Integer> createControlSchemaIfNotExists() {
    return Mono.fromCallable(() -> jdbcTemplate.update(InfraMetaModelQueryFactory.createControlSchema(), EMPTY_MAP))
        .onErrorResume(throwable -> throwable instanceof BadSqlGrammarException
            ? checkIfExistsPSQLErrors(throwable)
            : Mono.just(RESULT_NOT_AFFECTED_ROWS));
  }

  private Mono<Integer> checkIfExistsPSQLErrors(Throwable e) {
    if (e.getCause() != null && e.getCause() instanceof PSQLException) {
      PSQLException psqlException = (PSQLException) e.getCause();
      stream(PSQLError.values())
          .forEach(psqlError -> {
            if (psqlException.getSQLState().equals(psqlError.error)) {
              LOGGER.info("Checked: " + psqlError.description);
            }
          });
    }
    return Mono.just(RESULT_AFFECTED_ROWS);
  }


  @Override
  public Mono<Integer> createControlTable(MetaModel metaModel) {
    return Mono.fromCallable(() -> jdbcTemplate.update(InfraMetaModelQueryFactory.createControlTable(), EMPTY_MAP))
        .doOnNext(integer -> jdbcTemplate.update(InfraMetaModelQueryFactory.insertVersionControl(metaModel), EMPTY_MAP))
        .onErrorResume(throwable -> throwable instanceof BadSqlGrammarException
            ? checkIfExistsPSQLErrors(throwable)
            : Mono.just(RESULT_NOT_AFFECTED_ROWS));
  }


  @Override
  public Mono<Integer> updateVersionControl(MetaModel metaModel) {
    return Mono.fromCallable(() -> jdbcTemplate.update(InfraMetaModelQueryFactory.deleteVersionControl(), EMPTY_MAP))
        .doOnNext(integer -> jdbcTemplate.update(InfraMetaModelQueryFactory.insertVersionControl(metaModel), EMPTY_MAP));
  }


  @Override
  public Mono<Integer> clearDatabase() {
    return Mono.just(RESULT_NOT_AFFECTED_ROWS);
  }
}
