package com.jordi.jimenez.guil.cosmic.core.domain.metamodel;


import com.jordi.jimenez.guil.cosmic.core.exception.DomainFieldNotFoundException;
import com.jordi.jimenez.guil.cosmic.core.exception.DuplicateFieldNameException;
import com.jordi.jimenez.guil.cosmic.core.exception.UniqueIdentifierFieldNotFoundException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DomainMetaModel {
  private String name;
  private Map<String, DomainFieldMetaModel> fields = new LinkedHashMap<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean existsField(String name) {
    return fields.containsKey(name);
  }

  public void putField(String name,
                       String queryName,
                       DomainFieldMetaModelType type,
                       Boolean isUniqueIdentifier,
                       Boolean isRequired) {
    if (this.fields.containsKey(name)) {
      throw new DuplicateFieldNameException(name);
    }

    DomainFieldMetaModel fieldToAdd = DomainFieldMetaModel.DomainFieldMetaModelBuilder.aDomainFieldMetaModel()
        .withName(name)
        .withQueryName(queryName)
        .withFieldType(type)
        .withIsUniqueIdentifier(isUniqueIdentifier)
        .withIsRequired(isRequired)
        .build();

    this.fields.put(name, fieldToAdd);
  }

  public DomainFieldMetaModel getField(String name) {
    return Optional
        .ofNullable(this.fields.get(name))
        .orElseThrow(() -> new DomainFieldNotFoundException(name));
  }

  public DomainFieldMetaModel getUniqueIdentifierField() {
    return this.fields.keySet().stream()
        .map(s -> this.fields.get(s))
        .filter(DomainFieldMetaModel::getUniqueIdentifier)
        .findFirst()
        .orElseThrow(() -> new UniqueIdentifierFieldNotFoundException(name));
  }

  public List<DomainFieldMetaModel> getNotUniqueIdentiferFields() {
    return this.fields.keySet().stream()
        .map(s -> this.fields.get(s))
        .filter(domainFieldMetaModel -> !domainFieldMetaModel.getUniqueIdentifier())
        .collect(Collectors.toList());
  }


  public Collection<DomainFieldMetaModel> getAllFields() {
    return this.fields.values();
  }


  public static DomainMetaModelBuilder builder(
      MetaModel.MetaModelBuilder parentBuilder, Consumer<DomainMetaModel> callback) {
    return new DomainMetaModelBuilder(parentBuilder, callback);
  }


  public static class DomainMetaModelBuilder {
    private DomainMetaModel instance = new DomainMetaModel();
    private MetaModel.MetaModelBuilder parentBuilder;
    private Consumer<DomainMetaModel> callback;


    private DomainMetaModelBuilder(MetaModel.MetaModelBuilder parentBuilder, Consumer<DomainMetaModel> callback) {
      this.parentBuilder = parentBuilder;
      this.callback = callback;
    }

    public DomainMetaModelBuilder withField(String name, String queryName, DomainFieldMetaModelType type) {
      this.instance.putField(name, queryName, type, false, false);
      return this;
    }

    public DomainMetaModelBuilder withRequiredField(String name, String queryName, DomainFieldMetaModelType type) {
      this.instance.putField(name, queryName, type, false, true);
      return this;
    }

    public DomainMetaModelBuilder withUniqueIdentifierField(String name, String queryName) {
      this.instance.putField(name, queryName, DomainFieldMetaModelType.INTEGER, true, true);
      return this;
    }

    public MetaModel.MetaModelBuilder end() {
      callback.accept(instance);
      return parentBuilder;
    }
  }
}
