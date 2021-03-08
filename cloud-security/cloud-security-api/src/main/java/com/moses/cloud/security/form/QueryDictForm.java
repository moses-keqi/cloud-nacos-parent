package com.moses.cloud.security.form;

import com.moses.cloud.orm.form.AbstractPageConditionForm;
import com.moses.cloud.security.po.Dict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午10:25
 * @Version 1.0
 **/
@Data
public class QueryDictForm extends AbstractPageConditionForm<Dict> {

    @ApiModelProperty(value = "编码")
    public String dictCode;

    @ApiModelProperty(value = "中文名称")
    public String dictNameCn;


    @ApiModelProperty(value = "英文名称")
    public String dictNameEn;

    @ApiModelProperty(value = "父字典ID")
    public String parentId;

    @ApiModelProperty(value = "字典类型 0 系统级，1选项级")
    public String systemFlag;
}
