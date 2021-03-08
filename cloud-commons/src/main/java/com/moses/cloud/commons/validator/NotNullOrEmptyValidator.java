package com.moses.cloud.commons.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;

public class NotNullOrEmptyValidator implements ConstraintValidator<NotNullOrEmpty, Object> {

    @Override
    public void initialize(NotNullOrEmpty annotation) {
        // nothing
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return !StringUtils.isEmpty((String) obj);
        }
        if (obj instanceof List<?>) {
            return ((List<?>) obj).size() > 0;
        }
        if (obj instanceof Map<?, ?>) {
            return ((Map<?, ?>) obj).size() > 0;
        }
        return true;
    }

}
