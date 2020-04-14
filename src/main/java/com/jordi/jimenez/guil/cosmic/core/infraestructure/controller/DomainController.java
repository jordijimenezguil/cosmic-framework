package com.jordi.jimenez.guil.cosmic.core.infraestructure.controller;

import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.InfraDatabaseRepository;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.MetaModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.BOOLEAN;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.FLOAT;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.INTEGER;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.LONG;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.STRING;
import static com.jordi.jimenez.guil.cosmic.core.domain.metamodel.FieldType.TIMESTAMP;


@RestController
@RequestMapping(value = "/domain")
public class DomainController {


  private InfraDatabaseRepository repository;

  public DomainController(InfraDatabaseRepository repository) {
    this.repository = repository;
  }


  @GetMapping
  @RequestMapping(value = "/info")
  public String info() {

    MetaModel metaModel = MetaModel.builder()
        .withDomain("client")
        .withUniqueIdentifierField("id", "id", INTEGER)
        .withField("name", "name", STRING)
        .withField("isEnabled", "is_enable", BOOLEAN)
        .withField("accountNumber", "account_number", LONG)
        .withField("discount", "discount", FLOAT)
        .withField("creationDate", "creation_date", TIMESTAMP)
        .end()
        .build();
    DomainMetaModel user = metaModel.getDomains().get("client");
    repository.createDomainSchema(user);
    repository.createDomainTable(user);

    return "ok";
  }


}
