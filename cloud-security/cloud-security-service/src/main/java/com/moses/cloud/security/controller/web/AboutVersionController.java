package com.moses.cloud.security.controller.web;

import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.utils.BeanUtils;
import com.moses.cloud.security.form.AboutVersionForm;
import com.moses.cloud.security.po.AboutVersion;
import com.moses.cloud.security.service.IAboutVersionService;
import com.moses.cloud.security.vo.AboutVersionAppVo;
import com.moses.cloud.security.vo.AboutVersionH5Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2021/2/1 下午2:49
 * @Version 1.0
 **/
@Api(tags="升级管理")
@RestController
@Slf4j
@RequestMapping(value = "/api/update-version")
public class AboutVersionController {

    @Autowired
    private IAboutVersionService aboutVersionService;

    /**
     * 获取详情
     * @return
     */
    @ApiOperation(value="app升级",notes="app升级")
    @PostMapping(value = "/app")
    public ResponseData<AboutVersionAppVo> getVersionAppDescription(@Valid @RequestBody AboutVersionForm aboutVersionVo, BindingResult result){
        if (result.hasErrors()){
            //参数异常处理
            return ResponseData.newInstanceOfInvalid(result);
        }
        AboutVersion aboutVersion = aboutVersionService.getVersionDescription(aboutVersionVo.getClient());
        AboutVersionAppVo aboutVersionAppVo = BeanUtils.copy(aboutVersion, AboutVersionAppVo.class);
        return ResponseData.newInstanceOfSuccess(aboutVersionAppVo);
    }

    /**
     * h5更新
     * @return
     */
    @ApiOperation(value="h5升级",notes="h5升级")
    @PostMapping(value = "/h5")
    public ResponseData<List<AboutVersionH5Vo>> getVersionH5Description(){
        List<AboutVersion> aboutVersionH5s = aboutVersionService.getVersionH5ListDescription();
        List<AboutVersionH5Vo> aboutVersionH5Vos = BeanUtils.copyList(aboutVersionH5s, AboutVersionH5Vo.class);
        return ResponseData.newInstanceOfSuccess(aboutVersionH5Vos);
    }
}
