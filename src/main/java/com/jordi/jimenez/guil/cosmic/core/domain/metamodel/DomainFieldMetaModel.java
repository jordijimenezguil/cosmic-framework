package com.jordi.jimenez.guil.cosmic.core.domain.metamodel;

public class DomainFieldMetaModel {
  private String name;
  private String queryName;
  private DomainFieldMetaModelType domainFieldMetaModelType;
  private Boolean isUniqueIdentifier;
  private Boolean isNullable;
  private Boolean isRequired;


  public String getName() {
    return name;
  }

  public String getQueryName() {
    return queryName;
  }

  public DomainFieldMetaModelType getDomainFieldMetaModelType() {
    return domainFieldMetaModelType;
  }

  public Boolean getUniqueIdentifier() {
    return isUniqueIdentifier;
  }

  public Boolean getNullable() {
    return isNullable;
  }

  public Boolean getRequired() {
    return isRequired;
  }

  public static final class DomainFieldMetaModelBuilder {
    private String name;
    private String queryName;
    private DomainFieldMetaModelType domainFieldMetaModelType;
    private boolean isUniqueIdentifier;
    private boolean isNullable;
    private boolean isRequired;

    private DomainFieldMetaModelBuilder() {
    }

    public static DomainFieldMetaModelBuilder aDomainFieldMetaModel() {
      return new DomainFieldMetaModelBuilder();
    }

    public DomainFieldMetaModelBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public DomainFieldMetaModelBuilder withQueryName(String queryName) {
      this.queryName = queryName;
      return this;
    }

    public DomainFieldMetaModelBuilder withFieldType(DomainFieldMetaModelType domainFieldMetaModelType) {
      this.domainFieldMetaModelType = domainFieldMetaModelType;
      return this;
    }

    public DomainFieldMetaModelBuilder withIsUniqueIdentifier(Boolean isUniqueIdentifier) {
      this.isUniqueIdentifier = isUniqueIdentifier;
      return this;
    }

    public DomainFieldMetaModelBuilder withIsNullable(Boolean isNullable) {
      this.isNullable = isNullable;
      return this;
    }

    public DomainFieldMetaModelBuilder withIsRequired(Boolean isRequired) {
      this.isRequired = isRequired;
      return this;
    }

    public DomainFieldMetaModel build() {
      DomainFieldMetaModel domainFieldMetaModel = new DomainFieldMetaModel();
      domainFieldMetaModel.name = name;
      domainFieldMetaModel.queryName = queryName;
      domainFieldMetaModel.domainFieldMetaModelType = domainFieldMetaModelType;
      domainFieldMetaModel.isUniqueIdentifier = this.isUniqueIdentifier;
      domainFieldMetaModel.isNullable = this.isNullable;
      domainFieldMetaModel.isRequired = this.isRequired;
      return domainFieldMetaModel;
    }
  }
}
