package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.DomainQueryGenerator;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.InfraDatabasePostgresqlRepository;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.QueryContainer;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.service.system.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfiguration {

  @Bean
  public DomainQueryGenerator getDomainQueryGenerator() {
    return new DomainQueryGenerator();
  }

  @Bean
  public QueryContainer getQueryContainer(FileService fileService) {
    return new QueryContainer(fileService);
  }


  @Bean
  public InfraDatabaseRepository getInfraDatabaseRepository(
      NamedParameterJdbcTemplate jdbcTemplate,
      DomainQueryGenerator domainQueryGenerator) {
    return new InfraDatabasePostgresqlRepository(jdbcTemplate, domainQueryGenerator);
  }


}
