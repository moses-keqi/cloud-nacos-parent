package com.moses.cloud.security.vo;

import com.moses.cloud.commons.vo.AbstractVo;
import lombok.Data;

import java.util.Date;

/**
 * @Author HanKeQi
 * @Date 2020/12/28 下午9:39
 * @Version 1.0
 **/
@Data
public class ExpireVo extends AbstractVo {

    private String userId;
    private Date expireTime;
}
