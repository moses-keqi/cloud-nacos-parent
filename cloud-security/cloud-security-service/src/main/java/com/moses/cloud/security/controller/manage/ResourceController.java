package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.orm.form.IdForm;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.ResourceConditionForm;
import com.moses.cloud.security.po.Resource;
import com.moses.cloud.security.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "资源管理")
@RestController
@RequestMapping("/mapi/security/resource")
public class ResourceController {

	@Autowired
	private IResourceService resourceService;

	/**
	 * 
	 * 功能 ：通过条件查询 作者 ： LPF
	 */
	@ApiOperation(value = "分页查询",notes = "\n1：根据条件分页查询")
	@PostMapping("pageByCondition")
	public ResponseData<IPage<Resource>> pageByCondition(@Valid @RequestBody ResourceConditionForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		IPage<Resource> resourceIPage = resourceService.pageByCondition(form);
		return ResponseData.<IPage<Resource>> builder().success().data(resourceIPage).build();
	}

	/**
	 * 
	 * 功能 ：新增资源 作者 ： LPF
	 */
	@ApiOperation(value = "新增资源",notes = ""
			+"\n1：校验是否存在相同资源"
			+"\n2：校验是否存在相同授权码"
			+"\n3：新增资源")
	@PostMapping("create")
	public ResponseData<String> create(@Valid @RequestBody Resource resource, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		resourceService.insertResource(resource);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：修改资源 作者 ： LPF
	 */
	@ApiOperation(value = "修改资源",notes = ""
			+"\n1：校验是否存在相同资源，如果存在返回消息，流程结束"
			+"\n2：校验是否存在相同授权码，如果存在返回消息，流程结束"
			+"\n3：修改资源")
	@PostMapping("update")
	public ResponseData<String> update(@Valid @RequestBody Resource resource, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		resourceService.updateResource(resource);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：删除 作者 ： LPF
	 */
	@ApiOperation(value = "删除",notes = ""
			+"\n1：校验是否存在与菜单绑定关系，如果存在返回消息，流程结束"
			+"\n2：删除资源")
	@PostMapping("delete")
	public ResponseData<String> delete(@Valid @RequestBody IdForm id, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		resourceService.deleteById(id.getId());
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：通过ID查询 作者 ： LPF
	 */
	@ApiOperation(value = "通过ID查询",notes="\n1：根据ResourceId查询资源信息")
	@PostMapping("findById")
	public ResponseData<Resource> findById(@Valid @RequestBody IdForm id, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		Resource resource = resourceService.selectById(id.getId());
		return ResponseData.<Resource> builder().success().data(resource).build();
	}
	
	/**
	 * 
	 * 功能 ：通过ID更新资源启用禁用 作者 ： WZY
	 */
	@ApiOperation(value = "启用或禁用",notes="\n1：根据disabled=True/False，禁用启用资源")
	@PostMapping("updateDisabled")
	public ResponseData<Resource> updateDisabled(@Valid @RequestBody DisabledForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		resourceService.updateDisabledById(form);
		return ResponseData.<Resource> builder().success().build();
	}
}
