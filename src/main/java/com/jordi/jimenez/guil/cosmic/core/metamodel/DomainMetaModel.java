package com.jordi.jimenez.guil.cosmic.core.metamodel;

import com.jordi.jimenez.guil.cosmic.core.exception.DomainFieldNotFoundException;
import com.jordi.jimenez.guil.cosmic.core.exception.DuplicateFieldNameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class DomainMetaModel {
  private String name;
  private Map<String, DomainField> fields = new HashMap<>();


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
