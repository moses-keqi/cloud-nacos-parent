package com.moses.cloud.commons.validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.invoke.MethodHandles;

/**
 * @Author HanKeQi
 * @Date 2021/1/8 下午3:29
 * @Version 1.0
 **/
@Slf4j
public class LengthFixedValidator implements ConstraintValidator<LengthFixed, CharSequence> {

    private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());

    private int min;

    private int max;

    private int fixed;

    public LengthFixedValidator() {
    }

    @Override
    public void initialize(LengthFixed parameters) {
        this.min = parameters.min();
        this.max = parameters.max();
        this.fixed = parameters.fixed();
        this.validateParameters();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            if (fixed != 0){
                int length = value.length();
                boolean fixedFlag =  fixed == length;
//                if (!fixedFlag){
//                    context.disableDefaultConstraintViolation();
//                    context.buildConstraintViolationWithTemplate(String.format("长度需要%d位", fixed)).addConstraintViolation();
//                }
                return fixedFlag;
            }
            int length = value.length();
            return length >= this.min && length <= this.max;
        }
    }

    private void validateParameters() {
        if (this.fixed < 0 ){
            throw LOG.getLengthCannotBeNegativeException();
        }
        if (this.min < 0) {
            throw LOG.getMinCannotBeNegativeException();
        } else if (this.max < 0) {
            throw LOG.getMaxCannotBeNegativeException();
        } else if (this.max < this.min) {
            throw LOG.getLengthCannotBeNegativeException();
        }
    }
}
