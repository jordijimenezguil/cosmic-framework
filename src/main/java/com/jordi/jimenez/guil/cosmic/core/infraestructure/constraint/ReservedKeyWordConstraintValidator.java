package com.jordi.jimenez.guil.cosmic.core.infraestructure.constraint;

import com.jordi.jimenez.guil.cosmic.core.constraint.KeyWordDomainNameChecked;
import com.jordi.jimenez.guil.cosmic.core.infraestructure.repository.postgresql.PostgresqlKeyWords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ReservedKeyWordConstraintValidator implements ConstraintValidator<KeyWordDomainNameChecked, String> {

  @Override
  public boolean isValid(String domainName, ConstraintValidatorContext context) {
    return !PostgresqlKeyWords.exists(domainName);
  }
}
