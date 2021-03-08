package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.orm.form.IdForm;
import com.moses.cloud.security.form.MenuWithResourceForm;
import com.moses.cloud.security.form.ResourceConditionForm;
import com.moses.cloud.security.po.Menu;
import com.moses.cloud.security.po.Resource;
import com.moses.cloud.security.service.IMenuService;
import com.moses.cloud.security.service.IResourceService;
import com.moses.cloud.security.vo.PermissionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("mapi/security/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	@Autowired
	private IResourceService resourceService;
	
	
	@ApiOperation(value = "查询权限树",notes ="" 
			+ "\n1：查询所有有效菜单"
			+ "\n2：查询有效资源（只查询已经关联到菜单的资源）"
			+ "\n3：查询菜单资源关联中间表"
			+ "\n4：根据菜单、资源，组合权限树"
			+ "\n5：以type区分菜单（men）与资源（resource）"
			+ "\n6：所属父级以parentId字段判断")
	@ApiOperationSort(value=1)
	@PostMapping(value="findMenu")
	public ResponseData<List<PermissionTreeVo>> findMenu(){
		List<PermissionTreeVo> permissionTreeVoList= menuService.findMenu("");
		return ResponseData.<List<PermissionTreeVo>> builder().success().data(permissionTreeVoList).build();
	}

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@ApiOperation(value = "创建菜单",notes=""
			+ "\n1：判断授权码是否重复，如果重复则返回重复消息，流程结束"
			+ "\n2：执行修改")
	@ApiOperationSort(value=2)
	@PostMapping(value = "create")
	public ResponseData<String> create(@Valid @RequestBody Menu menu, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		menuService.createMenu(menu);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 根据ID查询菜单
	 * 
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "根据ID查询菜单",notes="\n1：根据菜单ID，单条查询数据实体")
	@ApiOperationSort(value=3)
	@PostMapping(value = "findById")
	public ResponseData<Menu> findById(@Valid @RequestBody IdForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		Menu menu = menuService.getById(form.getId());
		return ResponseData.<Menu> builder().success().data(menu).build();
	}

	/**
	 * 修改菜单
	 */
	@ApiOperation(value = "修改菜单",notes=""
			+ "\n1：判断授权码是否重复，如果重复则返回重复消息，流程结束"
			+ "\n2：执行修改")
	@ApiOperationSort(value=4)
	@PostMapping(value = "update")
	public ResponseData<String> update(@Valid @RequestBody Menu menu, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		menuService.updateMenu(menu);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 删除菜单
	 * 
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "删除菜单",notes=""
			+ "\n1：判断是否存在子级菜单，如果存在则返回消息，流程结束"
			+ "\n2：判断是否与用户存在绑定关系，如果存在则返回消息，流程结束"  
			+ "\n3：判断是否与资源存在绑定关系，如果存在则返回消息，流程结束"
			+ "\n4：执行删除")
	@ApiOperationSort(value=5)
	@PostMapping(value = "deleteById")
	public ResponseData<String> deleteById(@Valid @RequestBody IdForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		menuService.deleteMenu(form.getId());
		return ResponseData.<String> builder().success().build();
	}
	
	/**
	 * 删除资源
	 * @return
	 */
	@ApiOperation(value = "删除资源",notes=""
			+ "\n1：判断资源与菜单是否存在绑定关系，如果存在则返回消息，流程结束"
			+ "\n2：执行删除")
	@ApiOperationSort(value=6)
	@PostMapping(value = "deleteResourceById")
	public ResponseData<String> deleteResourceById(@Valid @RequestBody IdForm form, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		resourceService.deleteById(form.getId());
		return ResponseData.<String> builder().success().build();
	}
	
	/**
	 * 分页查询资源
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "分页查询资源",notes=""
			+ "\n1：根据条件分页查询资源信息")
	@ApiOperationSort(value=7)
	@PostMapping("pageResourceByCondition")
	public ResponseData<IPage<Resource>> pageResourceByCondition(@Valid @RequestBody ResourceConditionForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		IPage<Resource> resourceIPage = resourceService.pageByCondition(form);
		return ResponseData.<IPage<Resource>> builder().success().data(resourceIPage).build();
	}
	
	/**
	 * 保存菜单与资源关联
	 * @param form
	 * @return
	 * 作者 ： WZY
	 */
	@ApiOperation(value = "保存菜单与资源关联",notes=""
			+ "\n1：根据菜单Id删除所有菜单与资源绑定关系"
			+ "\n2：批量新增菜单资源绑定关系")
	@ApiOperationSort(value=8)
	@PostMapping(value = "saveMenuWithResource")
	public ResponseData<String> saveMenuWithResource(@Valid @RequestBody MenuWithResourceForm form, BindingResult result){
		menuService.saveMenuWithResource(form);
		return ResponseData.<String> builder().success().build();
	}
	
	/**
	    *  根据菜单Id查询绑定的资源
	 * @param form
	 * @return
	 * 作者 ： GJY
	 */
	@ApiOperation(value = "根据菜单Id查询绑定的资源",notes="\n1：根据菜单Id获取已经绑定的资源列表")
	@ApiOperationSort(value=9)
	@PostMapping(value = "findResourceByMenuId")
	public ResponseData<List<Resource>> findResourceByMenuId(@Valid @RequestBody IdForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		List<Resource> rList = menuService.findResourceByMenuId(form.getId());
		return ResponseData.<List<Resource>>builder().success().data(rList).build();
	}
	
	/**
	 * 根据菜单Id与资源Id删除菜单资源绑定关系
	 * @param form
	 * @return
	 * 作者 ： WZY
	 */
	@ApiOperation(value = "根据菜单Id与资源Id删除菜单资源绑定关系",notes=""
			+ "\n1：获取菜单Id与资源Id"
			+ "\n2：删除菜单与资源绑定关系")
	@ApiOperationSort(value=10)
	@PostMapping(value = "deleteBindByResourceIdAndMenuId")
	public ResponseData<List<Resource>> deleteBindByResourceIdAndMenuId(@Valid @RequestBody MenuWithResourceForm form, BindingResult result) {
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		menuService.deleteBindByResourceIdAndMenuId(form);
		return ResponseData.<List<Resource>>builder().success().build();
	}
}
