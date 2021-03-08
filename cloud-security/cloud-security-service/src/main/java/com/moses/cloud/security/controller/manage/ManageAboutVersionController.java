package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.security.form.AddAboutVersionForm;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.FindAboutVersionAppForm;
import com.moses.cloud.security.service.IAboutVersionService;
import com.moses.cloud.security.vo.FindAboutVersionAppVo;
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

/**
 * @author sunxiangkai
 */
@Api(tags="版本管理")
@RestController
@Slf4j
@RequestMapping(value = "/mapi/version")
public class ManageAboutVersionController {

    @Autowired
    private IAboutVersionService aboutVersionService;


    /**
     * 新增版本
     * @return
     */
    @ApiOperation(value="新增版本",notes="新增版本")
    @PostMapping(value = "/addVersion")
    public ResponseData addVersionAppDescription(@Valid @RequestBody AddAboutVersionForm aboutVersionVo, BindingResult result){
        if (result.hasErrors()){
            //参数异常处理
            return ResponseData.newInstanceOfInvalid(result);
        }
        aboutVersionService.addVersionAppDescription(aboutVersionVo);
        return ResponseData.newInstanceOfSuccess();
    }

    /**
     * 启用禁用版本
     * @return
     */
    @ApiOperation(value="启用禁用版本",notes="启用禁用版本")
    @PostMapping(value = "/updateVersion")
    public ResponseData updateVersionAppDescription(@Valid @RequestBody DisabledForm disabledForm, BindingResult result){
        if (result.hasErrors()){
            //参数异常处理
            return ResponseData.newInstanceOfInvalid(result);
        }
        aboutVersionService.updateDisabledVersion(disabledForm);
        return ResponseData.newInstanceOfSuccess();
    }

    /**
     * 查询版本
     * @return
     */
    @ApiOperation(value="查询版本",notes="查询版本")
    @PostMapping(value = "/findVersionAppDescription")
    public ResponseData<IPage<FindAboutVersionAppVo>> findVersionAppDescription(@Valid @RequestBody FindAboutVersionAppForm findAboutVersionAppForm, BindingResult result){
        if (result.hasErrors()){
            //参数异常处理
            return ResponseData.newInstanceOfInvalid(result);
        }
        IPage<FindAboutVersionAppVo> page = aboutVersionService.findVersionAppDescription(findAboutVersionAppForm);
        return ResponseData.<IPage<FindAboutVersionAppVo>> builder().success().data(page).build();
    }


}
