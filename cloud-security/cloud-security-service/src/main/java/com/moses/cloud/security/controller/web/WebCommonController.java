package com.moses.cloud.security.controller.web;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.security.SecurityConstants;
import com.moses.cloud.commons.utils.*;
import com.moses.cloud.commons.vo.CacheDictVo;
import com.moses.cloud.security.form.DeviceForm;
import com.moses.cloud.security.po.Device;
import com.moses.cloud.security.service.IDeviceService;
import com.moses.cloud.security.service.ISecurityService;
import com.moses.cloud.security.vo.DeviceVo;
import com.moses.cloud.security.vo.OffLineVo;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hankeqi
 */
@Api(tags="web公共接口管理")
@RestController
@RequestMapping("api/common")
@Slf4j
public class WebCommonController {

	@Autowired
	private ISecurityService securityService;

	@Autowired
	private IDeviceService deviceService;

	@ApiOperation(value="查询所有字典",notes="\n查询所有选项字典")
	@PostMapping(value="dict")
	public ResponseData<Map<String,List<CacheDictVo>>> dict(){
		Map<String,List<CacheDictVo>>  map = RedisUtils.opsForHashGetAll(DictKeyInterface.CATEGORY_CACHE_PUBLIC);
		return ResponseData.newInstanceOfSuccess(map);
	}

	@ApiOperation(value="查询权限信息",notes="\n查询当前用户授权码")
	@PostMapping(value="permission")
	public ResponseData<Set<String>> permission (){
		Set<String> permissions = securityService.findResourceAuthorizationCode(SecurityUtils.getCurrentUserId(), TokenUtils.getAppType());
		return ResponseData.newInstanceOfSuccess(permissions);
	}

	@ApiOperation(value="获取ID",notes="/n生成一个新的ID")
	@PostMapping(value="generateId")
	public ResponseData<String> generateId(){
		String id = IdWorker.getIdStr();
		return ResponseData.<String>builder().success().data(id).build();
	}


	@ApiOperation(value="登录检测",notes="\n登录检测")
	@PostMapping(value="system-device")
	public ResponseData<DeviceVo> sysDeviceCheck(@Valid @RequestBody DeviceForm deviceForm, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		String deviceId = ServletUtils.getHeader(SecurityConstants.DEVICE_ID);
		String client = ServletUtils.getHeader(SecurityConstants.CLIENT);
		//查数据库
		Device device = new Device();
		device.setUsername(deviceForm.getUsername());
		device.setDeviceId(deviceId);
		device.setClient(client);
		DeviceVo deviceVo = deviceService.findByDeviceCheck(device);
		return ResponseData.newInstanceOfSuccess(deviceVo);
	}

	@ApiOperation(value="缓存路由地址",notes="\n缓存路由地址")
	@PostMapping(value="system-off-line")
	public ResponseData<OffLineVo> getOffLine(){
		//默认走缓存
		boolean enable = Boolean.TRUE.booleanValue();
		//默认一周
		int dateDay = 7;
		//缓存URL
		List<OffLineVo.CacheUrl> cacheUrls = Lists.newArrayList();
		try {
			List<CacheDictVo> list = DictUtils.findByCategoryCode("cache.route.url");
			list.forEach(cacheDictVo -> {
				OffLineVo.CacheUrl cacheUrl = new OffLineVo.CacheUrl(cacheDictVo.getDictValue(), cacheDictVo.getNameCn());
				cacheUrls.add(cacheUrl);
			});
			//缓存开关
			CacheDictVo enableCacheDictVo = DictUtils.findByCode("cache.route.enable");
			enable = enableCacheDictVo != null ? Boolean.valueOf(enableCacheDictVo.getDictValue()) : enable;
			//缓存时长
			CacheDictVo dateDayCacheDictVo = DictUtils.findByCode("cache.route.date.day");
			dateDay = dateDayCacheDictVo != null ? Integer.valueOf(dateDayCacheDictVo.getDictValue()).intValue() : dateDay;

		}catch (Exception e){
			e.printStackTrace();
		}

		OffLineVo offLineVo = new OffLineVo();
		offLineVo.setCurrentDate(String.valueOf(System.currentTimeMillis()));
		offLineVo.setCacheUrls(cacheUrls);
//		offLineVo.setCacheDate(String.valueOf(DateUtils.daysAfter(new Date(), dateDay).getTime()));
		offLineVo.setCacheDate(String.valueOf(new Date().getTime()));
		offLineVo.setCacheEnable(enable);
		return ResponseData.newInstanceOfSuccess(offLineVo);
	}

}
