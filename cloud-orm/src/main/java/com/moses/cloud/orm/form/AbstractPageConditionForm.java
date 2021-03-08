package com.moses.cloud.orm.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 上午10:21
 * @Version 1.0
 **/
@Data
public abstract class AbstractPageConditionForm<T> extends AbstractForm {

    @ApiModelProperty(value = "当前页数，默认为 1")
    private Long currentPage = 1L;
    @ApiModelProperty(value = "第页大小，默认为 10")
    private Long pageSize = 10L;

    public Page<T> toPage(){
        Page page =new Page<>(this.currentPage, this.pageSize);
        return page;
    }
}
