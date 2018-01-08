package net.rokyinfo.insurance.controller;

import java.util.List;
import java.util.Map;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.InsSolutionEntity;
import net.rokyinfo.insurance.service.InsSolutionService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 保险产品方案表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@RestController
@RequestMapping("/api-order/v3.1/inssolution")
public class InsSolutionController {

	@Autowired
	private InsSolutionService insSolutionService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "保险产品方案列表", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
	)
	@GetMapping("/")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InsSolutionEntity> insSolutionList = insSolutionService.queryList(query);
		int total = insSolutionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(insSolutionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 详情
	 */
	@ApiIgnore
	@ApiOperation(value = "详情", notes = "")
	@ApiImplicitParam(name = "id", value = "", required = true, dataType = "Integer", paramType = "path")
	@GetMapping("/{id}")
	public R info(@PathVariable("id") Long id){
		InsSolutionEntity insSolution = insSolutionService.queryObject(id);
		
		return R.ok().put("insSolution", insSolution);
	}
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "新增", notes = "")
	@ApiImplicitParam(name = "insSolution", value = "", required = true, dataType = "InsSolutionEntity")
	@PostMapping("/")
	public R save(@RequestBody InsSolutionEntity insSolution){
		insSolutionService.save(insSolution);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改", notes = "")
	@ApiImplicitParam(name = "insSolution", value = "", required = true, dataType = "InsSolutionEntity")
	@PutMapping("/")
	public R update(@RequestBody InsSolutionEntity insSolution){
		insSolutionService.update(insSolution);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除", notes = "")
	@ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
	@DeleteMapping("/")
	public R delete(@RequestBody Long[] ids){
		insSolutionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
