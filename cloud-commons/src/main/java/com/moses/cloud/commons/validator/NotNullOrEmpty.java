package com.moses.cloud.commons.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullOrEmptyValidator.class)
@Documented
public @interface NotNullOrEmpty {

    // default error message
    String message() default "此项不能为空！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
