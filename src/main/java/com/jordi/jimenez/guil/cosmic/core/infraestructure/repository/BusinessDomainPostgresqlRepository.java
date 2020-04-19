package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainRepository;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.queryfactory.BusinessDomainQueryFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public class BusinessDomainPostgresqlRepository implements BusinessDomainRepository {
  private final static Map<String, Object> EMPTY_MAP = new HashMap<>();

  private NamedParameterJdbcTemplate jdbcTemplate;

  public BusinessDomainPostgresqlRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public Mono<Boolean> create(BusinessDomain businessDomain) {
    String insertQuery = BusinessDomainQueryFactory.createInsert(businessDomain);
    return Mono.fromCallable(() -> jdbcTemplate.update(insertQuery, EMPTY_MAP))
        .map(integer -> TRUE);
  }


}
