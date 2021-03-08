package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractForm;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2021/2/4 下午3:30
 * @Version 1.0
 **/
@Data
public class UpdateDisabledForm extends AbstractForm {

    public String id;

    public Boolean disabled;
}
