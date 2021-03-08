package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.utils.DictKeyInterface;
import com.moses.cloud.commons.utils.RedisUtils;
import com.moses.cloud.commons.utils.SecurityUtils;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.config.DictUtilsConfig;
import com.moses.cloud.security.service.ISecurityProvideService;
import com.moses.cloud.security.service.ISecurityService;
import com.moses.cloud.security.vo.MenuTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(tags="公共接口管理")
@RestController
@RequestMapping("/mapi/common")
public class CommonController {

	//	@Resource
//	private	ControllerScanner scanner;
	@Resource
	private ISecurityService securityService;

	@Autowired
	private ISecurityProvideService securityProvideService;

	@Autowired
	private DictUtilsConfig dictUtilsConfig;
	/**
	 * 查询菜单
	 * @return
	 */
	@ApiOperation(value="查询菜单",notes="\n查询当前用户菜单")
	@PostMapping(value="menu")
	public ResponseData<List<MenuTreeVo>> menu(){
		List<MenuTreeVo> data = securityService.findMenuByUserId(SecurityUtils.getCurrentUserId(),securityProvideService.getAppType());
		return ResponseData.<List<MenuTreeVo>>builder().success().data(data).build();
	}

	@ApiOperation(value="查询权限信息",notes="\n查询当前用户授权码")
	@PostMapping(value="permission")
	public ResponseData<Set<String>> permission (){
		Set<String> permissions = securityProvideService.findResourceByUsername(SecurityUtils.getCurrentUsername());
		return ResponseData.<Set<String>>builder().success().data(permissions).build();

	}

//	@ApiOperation(value="扫描接口")
//	@PostMapping(value="scanApis")
//	public ResponseData<String> scanApis (){
//		scanner.scan("com.moses", Controller.class,Constants.APP_TYPE_PC);
//		return ResponseData.<String>builder().success().build();
//	}

	@ApiOperation(value="获取ID",notes="/n生成一个新的ID")
	@PostMapping(value="generateId")
	public ResponseData<String> generateId(){
		String id = IdWorker.getIdStr();
		return ResponseData.newInstanceOfSuccess(id);
	}

	@ApiOperation(value="刷新缓存接口")
	@PostMapping(value="refreshDict")
	public ResponseData<String> refreshDict(){
		dictUtilsConfig.initDict();
		return ResponseData.newInstanceOfSuccess();
	}

	@ApiOperation(value="查询所有字典",notes="\n查询所有选项字典")
	@PostMapping(value="dict")
	public ResponseData<Map<String,List<CacheDictVo>>> dict(){
		Map<String,List<CacheDictVo>> cacheDictVos = RedisUtils.opsForHashGetAll(DictKeyInterface.CATEGORY_CACHE_PUBLIC);
		return ResponseData.<Map<String,List<CacheDictVo>>>builder().success().data(cacheDictVos).build();
	}

}
