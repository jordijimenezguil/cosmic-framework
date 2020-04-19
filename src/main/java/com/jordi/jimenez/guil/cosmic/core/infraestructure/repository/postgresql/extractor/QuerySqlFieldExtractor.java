package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor;

import com.jordi.jimenez.guil.cosmic.core.domain.businessDomain.BusinessDomain;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModel;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainFieldMetaModelType;
import com.jordi.jimenez.guil.cosmic.core.domain.metamodel.DomainMetaModel;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.SqlOption;

import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.COMMA;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.EMPTY;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.ENTER;
import static com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.masterdata.Character.SPACE;

public class QuerySqlFieldExtractor {

  private FieldExtractorSetting fieldExtractorSetting;

  public QuerySqlFieldExtractor(FieldExtractorSetting fieldExtractorSetting) {
    this.fieldExtractorSetting = fieldExtractorSetting;
  }

  public String extract(BusinessDomain businessDomain) {
    return processExtract(businessDomain.getDomainMetaModel(), businessDomain);
  }


  public String extract(DomainMetaModel domainMetaModel) {
    return processExtract(domainMetaModel, null);
  }

  private String processExtract(DomainMetaModel domainMetaModel, BusinessDomain businessDomain) {
    StringBuilder sb = new StringBuilder();
    String jumpLine = fieldExtractorSetting.getIncludeJumpLine() ? ENTER : EMPTY;

    int counter = 0;
    for (DomainFieldMetaModel domainFieldMetaModel : domainMetaModel.getAllFields()) {
      boolean isLastElement = counter == domainMetaModel.getAllFields().size() - 1;
      sb.append(generateSQLParamField(domainFieldMetaModel, businessDomain, isLastElement));

      sb.append(jumpLine);
      counter++;
    }

    return sb.toString();
  }


  private String generateSQLParamField(DomainFieldMetaModel domainFieldMetaModel,
                                       BusinessDomain businessDomain,
                                       boolean isLastField) {
    String nameField = fieldExtractorSetting.getIncludeName()
        ? domainFieldMetaModel.getName() + SPACE
        : EMPTY;

    String valueField = businessDomain != null && fieldExtractorSetting.getIncludeValue()
        ? businessDomain.getDomainData().get(domainFieldMetaModel.getName()) + SPACE
        : EMPTY;

    String sqlType = EMPTY;
    if (fieldExtractorSetting.getIncludeSqlType()) {
      sqlType = (domainFieldMetaModel.getUniqueIdentifier()
          ? DomainFieldMetaModelType.IDENTIFIER.sqlType
          : domainFieldMetaModel.getDomainFieldMetaModelType().sqlType) + SPACE;
    }

    String primaryKey = EMPTY;
    if (fieldExtractorSetting.getIncludeSqlPrimaryKey()) {
      primaryKey = domainFieldMetaModel.getUniqueIdentifier() ? SqlOption.PRIMARY_KEY : EMPTY;

    }

    String separator = isLastField ? EMPTY : COMMA;

    return SPACE
        + nameField
        + valueField
        + sqlType
        + primaryKey
        + separator;
  }

}
