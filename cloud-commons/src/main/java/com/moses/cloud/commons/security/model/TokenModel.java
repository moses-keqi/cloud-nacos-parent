package com.moses.cloud.commons.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HanKeQi
 * @Date 2020/12/25 下午10:49
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class TokenModel {

    private String id;

    private String username;

    private String name;

    private String utoken;

    private String phoneNo;

    private boolean vCode;

    public TokenModel(String utoken, String username){
        setUtoken(utoken);
        setUsername(username);
    }

}
