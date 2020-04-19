package com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.extractor;

public class FieldExtractorSetting {
  private Boolean includeName;
  private Boolean includeValue;
  private Boolean includeSqlType;
  private Boolean includeSqlPrimaryKey;
  private Boolean includeJumpLine;

  public Boolean getIncludeName() {
    return includeName;
  }

  public Boolean getIncludeValue() {
    return includeValue;
  }

  public Boolean getIncludeSqlType() {
    return includeSqlType;
  }

  public Boolean getIncludeSqlPrimaryKey() {
    return includeSqlPrimaryKey;
  }

  public Boolean getIncludeJumpLine() {
    return includeJumpLine;
  }


  public static final class FieldExtractorSettingBuilder {
    private boolean includeName;
    private boolean includeValue;
    private boolean includeSqlType;
    private boolean includeSqlPrimaryKey;
    private boolean jumpLine;

    private FieldExtractorSettingBuilder() {
    }

    public static FieldExtractorSettingBuilder aFieldExtractorSetting() {
      return new FieldExtractorSettingBuilder();
    }

    public FieldExtractorSettingBuilder includeName() {
      this.includeName = true;
      return this;
    }

    public FieldExtractorSettingBuilder includeValue() {
      this.includeValue = true;
      return this;
    }

    public FieldExtractorSettingBuilder includeSqlType() {
      this.includeSqlType = true;
      return this;
    }

    public FieldExtractorSettingBuilder includeSqlPrimaryKey() {
      this.includeSqlPrimaryKey = true;
      return this;
    }

    public FieldExtractorSettingBuilder withColumnFormat() {
      this.jumpLine = true;
      return this;
    }

    public FieldExtractorSetting build() {
      FieldExtractorSetting fieldExtractorSetting = new FieldExtractorSetting();
      fieldExtractorSetting.includeSqlType = this.includeSqlType;
      fieldExtractorSetting.includeSqlPrimaryKey = this.includeSqlPrimaryKey;
      fieldExtractorSetting.includeName = this.includeName;
      fieldExtractorSetting.includeJumpLine = this.jumpLine;
      fieldExtractorSetting.includeValue = this.includeValue;
      return fieldExtractorSetting;
    }
  }
}