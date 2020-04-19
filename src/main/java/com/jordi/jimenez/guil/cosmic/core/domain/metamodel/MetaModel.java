package com.jordi.jimenez.guil.cosmic.core.domain.metamodel;

import com.jordi.jimenez.guil.cosmic.core.exception.DomainNotFoundException;
import com.jordi.jimenez.guil.cosmic.core.exception.DuplicateDomainNameException;

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
      if (instance.domains.containsKey(name)) {
        throw new DuplicateDomainNameException(name);
      }

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


  public String generateVersionHash() {
    StringBuilder sb = new StringBuilder();
    domains.values()
        .forEach(domain -> {
          sb.append(domain.getName());
          domain.getAllFields()
              .forEach(f -> sb.append(f.getName())
                  .append(f.getQueryName())
                  .append(f.getDomainFieldMetaModelType().javaType)
                  .append(f.getNullable())
                  .append(f.getUniqueIdentifier()));
        });
    return sb.toString();
  }

  public DomainMetaModel getDomainMetaModelByName(String domainName) {
    DomainMetaModel domainMetaModel = this.domains.get(domainName);
    if (domainMetaModel == null) {
      throw new DomainNotFoundException(domainName);
    }

    return domainMetaModel;
  }

}
