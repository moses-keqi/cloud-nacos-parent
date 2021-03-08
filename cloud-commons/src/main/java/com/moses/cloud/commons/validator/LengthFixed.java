package com.moses.cloud.commons.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @Author HanKeQi
 * @Date 2021/1/8 下午3:29
 * @Version 1.0
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = LengthFixed.List.class)
@Constraint(validatedBy = LengthFixedValidator.class)
@Documented
public @interface LengthFixed {

    int min() default 0;

    int max() default 2147483647;

    int fixed() default 0;

    String message() default "{org.hibernate.validator.constraints.Length.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        LengthFixed[] value();
    }
}
