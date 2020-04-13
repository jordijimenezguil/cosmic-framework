package com.jordi.jimenez.guil.cosmic.core.metamodel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MetaModel {

  private Map<String, DomainMetaModel> domains = new HashMap<>();

  public Map<String, DomainMetaModel> getDomains() {
    return domains;
  }

  public static MetaModelBuilder builder() {
    return new MetaModelBuilder();
  }


  public static final class MetaModelBuilder {
    private MetaModel instance = new MetaModel();


    private MetaModelBuilder() {
    }


    public DomainMetaModel.DomainMetaModelBuilder withDomain(String name) {
      Consumer<DomainMetaModel> callback = domainToAdd -> {
        domainToAdd.setName(name);
        instance.domains.put(domainToAdd.getName(), domainToAdd);
      };
      return DomainMetaModel.builder(this, callback);
    }

    public MetaModel build() {
      return instance;
    }
  }
}
