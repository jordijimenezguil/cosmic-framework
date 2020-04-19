package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.InfraDatabasePostgresqlRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class InfraDatabaseConfiguration {


  @Bean
  public InfraDatabaseRepository getInfraDatabaseRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    return new InfraDatabasePostgresqlRepository(jdbcTemplate);
  }


}
