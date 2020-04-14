package com.jordi.jimenez.guil.cosmic.core.constraint;

import com.jordi.jimenez.guil.cosmic.core.infraestructure.constraint.ReservedKeyWordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ReservedKeyWordConstraintValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface KeyWordDomainNameChecked {
  String message() default "Invalid Domain Name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}