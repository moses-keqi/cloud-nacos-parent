package com.moses.cloud.security.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moses.cloud.commons.msg.ResponseData;
import com.moses.cloud.orm.form.IdForm;
import com.moses.cloud.security.form.QueryDictForm;
import com.moses.cloud.security.form.UpdateDisabledForm;
import com.moses.cloud.security.po.Dict;
import com.moses.cloud.security.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author HanKeQi
 */
@Api(tags="系统级字典管理")
@RestController
@RequestMapping("/mapi/system/sysdict")
public class DictController {
       
	@Autowired
	private IDictService dictService;
	
	/**
	 * 
	 * 功能 ：通过条件查询字典
	 * 作者 ： LPF
	 */
	@ApiOperation(value="通过条件查询字典",notes = ""
			+"\n1：字典数据查询"
			+"\n2：编码（dictCode），中文名称（dictNameCn），英文名称（dictNameCn），模糊查询"
			+"\n3：父级（parentId），字典类型（SYSTEM_FLAG，0 系统级，1选项级）精确查询"
			+"\n4：返回数据列表")
	@PostMapping("pageByCondition")
	public ResponseData<IPage<Dict>> pageByCondition(@Valid @RequestBody QueryDictForm form, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		IPage<Dict> results = dictService.pageByCondition(form);
		return ResponseData.<IPage<Dict>>builder().success().data(results).build();
	}
	
	/**
	 * 
	 * 功能 ：新增字典 
	 * 作者 ： LPF
	 */
	@ApiOperation(value="新增字典",notes=""
			+"\n1：系统级字典新增"
			+"\n2：默认（systemFlag）系统级0"
			+"\n3：中文名字，编码，字典值取值，是否显示，非空验证"
			+"\n4：编码dictCode重复校验"
			+"\n5：新增字典")
	@PostMapping("create")
	public ResponseData<String> create(@Valid @RequestBody Dict dict, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		dict.setSystemFlag(0);
		dictService.insertDict(dict);
		return ResponseData.<String>builder().success().build();
	}
	
	/**
	 * 
	 * 功能 ：通过ID查询
	 * 作者 ： LPF
	 */
	@ApiOperation(value="通过ID查询",notes="\n1：根据Id查询字典信息")
	@PostMapping("findById")
	public ResponseData<Dict> findById(@Valid @RequestBody IdForm form, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		Dict dict = dictService.getById(form.getId());
		return ResponseData.<Dict>builder().success().data(dict).build();
	}
	
	/**
	 * 
	 * 功能 ：修改字典
	 * 作者 ： LPF
	 */
	@ApiOperation(value="修改字典",notes=""
			+"\n1：系统级字典修改"
			+"\n2：默认（systemFlag）系统级0"
			+"\n3：中文名字，编码，字典值取值，是否显示，非空验证"
			+"\n4：编码dictCode重复校验（排除当前）"
			+"\n5：执行修改")
	@PostMapping("update")
	public ResponseData<String> update(@Valid @RequestBody Dict dict, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		dict.setSystemFlag(0);
		dictService.updateDict(dict);
		return ResponseData.<String>builder().success().build();
	}
	
	/**
	 * 
	 * 功能 ：删除
	 * 作者 ： LPF
	 */
	@ApiOperation(value="删除",notes=""
			+"\n1：单条数据删除"
			+"\n1：校验是否存在子集，如果存在，不能删除"
			+"\n2：执行物理删除")
	@PostMapping("delete")
	public ResponseData<String> delete(@Valid @RequestBody IdForm form, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		dictService.deleteDictById(form.getId());
		return ResponseData.<String>builder().success().build();
	}
	
	/**
	 * 
	 * 功能 ：是否可用 是    1 否    0
	 * 作者 ： LPF
	 */
	@ApiOperation(value="是否可用",notes=""
			+"\n1：禁用/启用功能"
			+"\n2：根据Id，disabled=True启用，false禁用"
			+"\n3：执行更新")
	@PostMapping("updateDisabled")
	public ResponseData<String> updateType(@Valid @RequestBody UpdateDisabledForm form, BindingResult result){
		if (result.hasErrors()){
			return ResponseData.newInstanceOfInvalid(result);
		}
		dictService.updateDisabled(form.getId(), form.getDisabled());
		return ResponseData.<String>builder().success().build();
		
	}
	
}
