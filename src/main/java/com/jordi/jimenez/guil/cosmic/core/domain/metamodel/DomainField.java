package com.jordi.jimenez.guil.cosmic.core.domain.metamodel;

public class DomainField {
  private String name;
  private String queryName;
  private FieldType fieldType;
  private Boolean  isUniqueIdentifier;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQueryName() {
    return queryName;
  }

  public void setQueryName(String queryName) {
    this.queryName = queryName;
  }

  public FieldType getFieldType() {
    return fieldType;
  }

  public void setFieldType(FieldType fieldType) {
    this.fieldType = fieldType;
  }

  public Boolean getUniqueIdentifier() {
    return isUniqueIdentifier;
  }

  public void setUniqueIdentifier(Boolean uniqueIdentifier) {
    isUniqueIdentifier = uniqueIdentifier;
  }


  public static final class DomainFieldBuilder {
    private String name;
    private String queryName;
    private FieldType fieldType;
    private Boolean  isUniqueIdentifier;

    private DomainFieldBuilder() {
    }

    public static DomainFieldBuilder aDomainField() {
      return new DomainFieldBuilder();
    }

    public DomainFieldBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public DomainFieldBuilder withQueryName(String queryName) {
      this.queryName = queryName;
      return this;
    }

    public DomainFieldBuilder withFieldType(FieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    public DomainFieldBuilder withIsUniqueIdentifier(Boolean isUniqueIdentifier) {
      this.isUniqueIdentifier = isUniqueIdentifier;
      return this;
    }

    public DomainField build() {
      DomainField domainField = new DomainField();
      domainField.setName(name);
      domainField.setQueryName(queryName);
      domainField.setFieldType(fieldType);
      domainField.isUniqueIdentifier = this.isUniqueIdentifier;
      return domainField;
    }
  }
}
