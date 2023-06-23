package com.kos.annotation;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
/**
 *  Class<?>[] groups() default {};
 * This attribute is used to specify validation groups.
 * Validation groups allow you to define different sets of constraints that can be applied selectively based on certain conditions.
 * By default, an empty array is used, indicating that no groups are specified.
 *
 * Class<? extends Payload>[] payload() default {};
 * The payload attribute is used to provide additional metadata about the validation constraint.
 * It is typically used by frameworks or custom validation implementations.
 * By default, an empty array is used, indicating no payload is associated with the constraint.
 */