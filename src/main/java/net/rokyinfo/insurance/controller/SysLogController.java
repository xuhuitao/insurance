package net.rokyinfo.insurance.controller;

import java.util.List;
import java.util.Map;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.SysLogEntity;
import net.rokyinfo.insurance.service.SysLogService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 系统操作日志
 * 
 * @author yangyang.cao
 * @email yangyang.cao@rokyinfo
 * @date 2018-01-22 15:40:12
 */
@RestController
@RequestMapping("/v1.0/syslog")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "日志列表", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "订单状态", required = false, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
	)
	@GetMapping("")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysLogEntity> sysLogList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());

		return new R<>(pageUtil);
	}


	@ApiIgnore
	@ApiOperation(value = "详情", notes = "")
	@ApiImplicitParam(name = "id", value = "", required = true, dataType = "Integer", paramType = "path")
	@GetMapping("/{id}")
	public R info(@PathVariable("id") Long id){

		SysLogEntity sysLog = sysLogService.queryObject(id);

		return new R<>(sysLog);
	}

	@ApiOperation(value = "新增", notes = "")
	@ApiImplicitParam(name = "sysLogEntity", value = "", required = true, dataType = "SysLogEntity")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public R save(@RequestBody SysLogEntity sysLog){

		sysLogService.save(sysLog);

		return new R<>();
	}
	
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改", notes = "")
	@ApiImplicitParam(name = "sysLogEntity", value = "", required = true, dataType = "SysLogEntity")
	@PutMapping("/update")
	public R update(@RequestBody SysLogEntity sysLog){

		sysLogService.update(sysLog);

		return new R<>();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除", notes = "")
	@ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
	@DeleteMapping("")
	public R delete(@RequestBody Long[] ids){

		sysLogService.deleteBatch(ids);

		return new R<>();
	}
	
}
