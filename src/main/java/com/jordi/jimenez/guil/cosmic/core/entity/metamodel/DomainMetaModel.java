package com.jordi.jimenez.guil.cosmic.core.entity.metamodel;


import com.jordi.jimenez.guil.cosmic.core.constraint.KeyWordDomainNameChecked;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.DomainFieldNotFoundException;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.DuplicateFieldNameException;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.exception.UniqueIdentifierFieldNotFoundException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DomainMetaModel {

  @KeyWordDomainNameChecked
  private String name;

  private Map<String, DomainField> fields = new LinkedHashMap<>();


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean existsField(String name) {
    return fields.containsKey(name);
  }

  public void putField(String name, String queryName, FieldType type, Boolean isUniqueIdentifier) {
    if (this.fields.containsKey(name)) {
      throw new DuplicateFieldNameException(name);
    }

    DomainField fieldToAdd = DomainField.DomainFieldBuilder.aDomainField()
        .withName(name)
        .withQueryName(queryName)
        .withFieldType(type)
        .withIsUniqueIdentifier(isUniqueIdentifier)
        .build();

    this.fields.put(name, fieldToAdd);
  }

  public DomainField getField(String name) {
    return Optional
        .ofNullable(this.fields.get(name))
        .orElseThrow(() -> new DomainFieldNotFoundException(name));
  }

  public DomainField getUniqueIdentifierField() {
    return this.fields.keySet().stream()
        .map(s -> this.fields.get(s))
        .filter(DomainField::getUniqueIdentifier)
        .findFirst()
        .orElseThrow(() -> new UniqueIdentifierFieldNotFoundException(name));
  }

  public List<DomainField> getNotUniqueIdentiferFields() {
    return this.fields.keySet().stream()
        .map(s -> this.fields.get(s))
        .filter(domainField -> !domainField.getUniqueIdentifier())
        .collect(Collectors.toList());
  }


  public Collection<DomainField> getAllFields() {
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


    public DomainMetaModelBuilder withField(String name, String queryName, FieldType type) {
      this.instance.putField(name, queryName, type, false);
      return this;
    }


    public DomainMetaModelBuilder withUniqueIdentifierField(String name, String queryName, FieldType type) {
      this.instance.putField(name, queryName, type, true);
      return this;
    }

    public MetaModel.MetaModelBuilder and() {
      callback.accept(instance);
      return parentBuilder;
    }


    public MetaModel.MetaModelBuilder end() {
      callback.accept(instance);
      return parentBuilder;
    }
  }
}
