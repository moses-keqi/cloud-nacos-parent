package com.moses.cloud.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moses.cloud.security.form.QueryDictForm;
import com.moses.cloud.security.po.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DictMapper extends BaseMapper<Dict> {

	/**
	 *
	 * 功能 ：删除字典
	 * 逻辑 ：查询父级下面是否有子级
	 * 作者 ： LPF
	 */
	List<Dict> selectByParentId(String parentId);

	/**
	 * 功能 ：新增、修改字典
	 * 逻辑 ：字典Code是否存在
	 * 作者 ： LPF
	 */
	Dict queryDictCode(String dictCode);

	/**
	 * 功能 ：	通过条件查询分页
	 * 作者 ： LPF
	 */
	IPage<Dict> pageByCondition(Page<Dict> page, @Param("condition") QueryDictForm form);

	/**
	 * 根据code集合查询
	 * @param code
	 * @return
	 */
	List<Dict> findByParentCode(String code);



}
