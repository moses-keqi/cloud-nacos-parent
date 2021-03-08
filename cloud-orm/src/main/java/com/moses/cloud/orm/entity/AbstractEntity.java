package com.moses.cloud.orm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moses.cloud.commons.utils.SecurityUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体对象 有唯一ID
 * @Author HanKeQi
 * @Date 2020/12/17 下午8:41
 * @Version 1.0
 **/
@Data
@ToString
public abstract class AbstractEntity extends AbstractPo implements Serializable {

    //分配UUID,主键类型为String(since 3.3.0),使用接口IdentifierGenerator的方法nextUUID(默认default方法)
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "创建人工号")
    private String createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改人工号")
    private String updateBy;

    @ApiModelProperty(value = "修改人姓名")
    private String updateName;

    @ApiModelProperty(value = "更新日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "删除状态，0 未删除，1删除")
    private Boolean deleted;

    public AbstractEntity(){

    }

    public AbstractEntity(Boolean flag){
        if (flag.booleanValue()){
            this.setCreateBy(SecurityUtils.getCurrentUsername());
            this.setCreateName(SecurityUtils.getCurrentName());
            this.setCreateTime(new Date());
            this.setDeleted(false);
            return;
        }
        if (flag.booleanValue()){
            this.setUpdateBy(SecurityUtils.getCurrentUsername());
            this.setUpdateName(SecurityUtils.getCurrentName());
            this.setUpdateTime(new Date());
            return;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        boolean equalsId = !StringUtils.isEmpty(id)?  !id.equals(that.id) : !StringUtils.isEmpty(id);
        if (equalsId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return !StringUtils.isEmpty(id) ? id.hashCode() : 0;
    }

}
