package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.commons.vo.IdVo;
import com.moses.cloud.orm.form.IdForm;
import com.moses.cloud.security.form.DisabledForm;
import com.moses.cloud.security.form.RoleConditionForm;
import com.moses.cloud.security.form.RoleWithPermissionForm;
import com.moses.cloud.security.po.Role;
import com.moses.cloud.security.service.IMenuService;
import com.moses.cloud.security.service.IRoleService;
import com.moses.cloud.security.vo.PermissionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("mapi/security/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IMenuService menuService;

	/**
	 * 
	 * 功能 ：通过条件查询字典 作者 ： LPF
	 */
	@ApiOperation(value = "分页查询", notes = ""
			+ "\n1：通过页数(currentPage)与每页大小(pageSize)控制分页"
			+ "\n2：根据名称、角色授权码、是否可用为条件进行查询，条件可为空"
			+ "\n3：若无条件只进行分页"
			+ "\n4：若有条件，则用and连接，如：where 条件1 and 条件2")
	@PostMapping("pageByCondition")
	public ResponseData<IPage<Role>> pageByCondition(@RequestBody RoleConditionForm roleCondition) {
		IPage<Role> result = roleService.pageByCondition(roleCondition);
		return ResponseData.<IPage<Role>> builder().success().data(result).build();
	}

	/**
	 * 
	 * 功能 ：新增角色 作者 ： LPF
	 */
	@ApiOperation(value = "新增角色", notes = ""
			+ "\n1：查询角色授权码是否存在，存在则抛出异常"
			+ "\n2：封装参数，delete与disable赋予默认值false，即未删除、可用。"
			+ "\n3：获取登录人信息"
			+ "\n4：封装审计参数，创建人ID，创建人账号，创建时间"
			+ "\n5：执行插入语句")
	@PostMapping("create")
	public ResponseData<String> create(@RequestBody Role Role) {
		roleService.insertRole(Role);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：修改角色 作者 ： LPF
	 */
	@ApiOperation(value = "修改角色", notes = ""
			+ "\n1：判断角色ID是否为空，为空则抛出异常"
			+ "\n2：查询角色授权码是否存在，存在则抛出异常"
			+ "\n3：获取登录人信息"
			+ "\n4：封装审计参数，修改人ID，修改人账号，修改时间"
			+ "\n5：执行更新语句")
	@PostMapping("update")
	public ResponseData<String> update(@RequestBody Role Role) {
		Role.setAppType(null);
		roleService.updateRole(Role);
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：删除 作者 ： LPF
	 */
	@ApiOperation(value = "删除", notes = ""
			+ "\n1：判断ID是否为空，为空抛出异常"
			+ "\n2：查询角色与用户是否存在关联关系，存在则抛出异常"
			+ "\n3：删除角色"
			+ "\n4：删除角色菜单关联关系"
			+ "\n5：删除角色资源关联关系")
	@PostMapping("delete")
	public ResponseData<String> delete(@RequestBody IdForm form) {
		roleService.deleteById(form.getId());
		return ResponseData.<String> builder().success().build();
	}

	/**
	 * 
	 * 功能 ：通过ID查询 作者 ： LPF
	 */
	@ApiOperation(value = "通过ID查询", notes = ""
			+ "\n1：获取要查询的角色ID"
			+ "\n2：执行查询语句")
	@PostMapping("findById")
	public ResponseData<Role> findById(@RequestBody IdForm form) {
		Role Role = roleService.selectById(form.getId());
		return ResponseData.<Role> builder().success().data(Role).build();
	}

	/**
	 * 
	 * 功能 ：是否可用（0 不可用，1 可用） 作者 ： LPF
	 */
	@ApiOperation(value = "是否可用（0 不可用，1 可用）", notes = ""
			+ "\n1：判断角色ID是否为空，为空则抛出异常"
			+ "\n2：查询角色信息"
			+ "\n3：角色状态(disable)根据参数修改"
			+ "\n4：执行更新语句")
	@PostMapping("updateDisabled")
	public ResponseData<String> updateDisabled(@RequestBody DisabledForm form) {
		roleService.updateDisabled(form.getId(), form.getDisabled());
		return ResponseData.<String> builder().success().build();

	}
	
	@ApiOperation(value = "查询权限树",notes = ""
			+ "\n1：查询所有有效菜单"
			+ "\n2：根据角色ID查询角色菜单关系"
			+ "\n3：根据角色ID查询角色资源关系"
			+ "\n4：根据菜单、资源，组合权限树"
			+ "\n5：以type区分菜单（men）与资源（resource）"
			+ "\n6：所属父级以parentId字段判断"
			+ "\n7：将与角色具有关联关系的菜单、资源设置为true（checked为true）")
	@PostMapping(value="findPermissionTree")
	public ResponseData<List<PermissionTreeVo>> findPermissionTree(@RequestBody IdVo id){
		List<PermissionTreeVo> list=menuService.findMenuRoleResource(id.getId(),id.getAppType());
		return ResponseData.<List<PermissionTreeVo>> builder().success().data(list).build();
	}

	@ApiOperation(value = "保存角色与权限关联", notes = ""
			+ "\n1：删除角色与菜单关联关系"
			+ "\n2：.删除角色与资源关联关系"
			+ "\n3：保存角色与菜单关联关系"
			+ "\n4：保存角色与资源关联关系")
	@PostMapping(value="saveRoleWithPermission")
	public ResponseData<String> saveRoleWithPermission(@RequestBody RoleWithPermissionForm form){
		roleService.saveRoleWithPermission(form);
		return ResponseData.<String> builder().success().build();
	}

}
