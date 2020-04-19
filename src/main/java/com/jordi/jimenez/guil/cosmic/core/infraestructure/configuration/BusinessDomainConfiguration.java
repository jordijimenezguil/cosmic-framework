package com.jordi.jimenez.guil.cosmic.core.infraestructure.configuration;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainFactory;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainParser;
import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomainRepository;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.BusinessDomainPostgresqlRepository;
import com.jordi.jimenez.guil.cosmic.core.usecase.BusinessDomainUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class BusinessDomainConfiguration {


  @Bean
  public BusinessDomainParser getBusinessDomainParser() {
    return new BusinessDomainParser();
  }

  @Bean
  public BusinessDomainFactory getBusinessDomainFactory(BusinessDomainParser businessDomainParser) {
    return new BusinessDomainFactory(businessDomainParser);
  }

  @Bean
  public BusinessDomainRepository getBusinessDomainRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    return new BusinessDomainPostgresqlRepository(jdbcTemplate);
  }

  @Bean
  public BusinessDomainUseCase getBusinessDomainUseCase(BusinessDomainRepository businessDomainRepository) {
    return new BusinessDomainUseCase(businessDomainRepository);
  }

}
