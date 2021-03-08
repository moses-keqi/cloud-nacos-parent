package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractForm;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:40
 * @Version 1.0
 **/
@Data
public class DisabledForm extends AbstractForm {

    private String id;

    private Boolean disabled;
}
