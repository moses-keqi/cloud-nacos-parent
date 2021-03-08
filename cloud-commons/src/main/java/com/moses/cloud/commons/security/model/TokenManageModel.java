package com.moses.cloud.commons.security.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author HanKeQi
 * @Date 2021/2/4 下午5:00
 * @Version 1.0
 **/
@Data
public class TokenManageModel implements Serializable {

    private String id;

    private String username;

    private String name;

    private String utoken;

    public TokenManageModel(String utoken, String username){
        setUtoken(utoken);
        setUsername(username);
    }

}
