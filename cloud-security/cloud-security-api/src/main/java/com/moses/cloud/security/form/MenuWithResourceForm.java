package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractForm;
import lombok.Data;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:26
 * @Version 1.0
 **/
@Data
public class MenuWithResourceForm extends AbstractForm {

    private String menuId;

    private List<String> resourceIds;
}
