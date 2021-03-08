package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moses.cloud.security.po.RoleResource;

import java.util.List;

/**
 * @Author HanKeQi
 * @Date 2020/12/30 上午10:55
 * @Version 1.0
 **/
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

	/**
	 *
	 * 功能：根据角色ID删除角色资源绑定关系
	 *
	 * @param roleId
	 */
	void deleteByRoleId(String roleId);

	/**
	 *
	 * 功能：根据角色ID查询角色资源绑定关系
	 *
	 * @param roleId
	 */
	List<RoleResource> findByRoleId(String roleId);
}
