package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moses.cloud.security.form.MenuConditionForm;
import com.moses.cloud.security.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午11:28
 * @Version 1.0
 **/
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 根据授权码查询菜单
	 * @param permissionString
	 * @return
	 */
	Menu findByPermissionString(String permissionString);
	
	/**
	 * 分页查询
	 * @param page
	 * @param condition
	 * @return
	 */
	IPage<Menu> pageByCondition(Page<Menu> page, @Param("condition") MenuConditionForm condition);

	/**
	 * 根据角色ID查询
	 * @param roleIds
	 * @return
	 */
	List<Menu> findByRoleIds(@Param("roleIds") List<String> roleIds);

	/**
	 * 功能：查询所有有效菜单
	 * @return
	 */
	List<Menu> findAllMenu(@Param("appType") String appType);

	/**
	 * 功能：根据父级ID查询子级菜单
	 * @param parentId
	 * @return
	 */
	List<Menu> finChildMenu(@Param("parentId") String parentId);
}
