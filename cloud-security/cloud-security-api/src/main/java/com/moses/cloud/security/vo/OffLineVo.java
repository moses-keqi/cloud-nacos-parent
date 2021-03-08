package com.moses.cloud.security.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.moses.cloud.commons.vo.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2021/2/3 下午2:45
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class OffLineVo extends AbstractVo {

    @ApiModelProperty("是否缓存： true 缓存 false 不缓存")
    private boolean cacheEnable;

    @ApiModelProperty("缓存时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String cacheDate;

    @ApiModelProperty("系统时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String  currentDate;

    @ApiModelProperty("缓存URL")
    private List<CacheUrl> cacheUrls;

    /**
     * 缓存
     */
    @AllArgsConstructor
    @Data
    public static class CacheUrl extends AbstractVo {

        @ApiModelProperty("缓存路由")
        private String cacheRouteUrl;

        @ApiModelProperty("名称")
        private String name;
    }
}
