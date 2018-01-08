package net.rokyinfo.insurance.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.InsProductEntity;
import net.rokyinfo.insurance.service.InsProductService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 保险产品表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@RestController
@RequestMapping("/api-order/v3.1/insproduct")
public class InsProductController {
	@Autowired
	private InsProductService insProductService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "保险产品列表", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
	)
	@GetMapping("")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<InsProductEntity> insProductList = insProductService.queryList(query);
		int total = insProductService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(insProductList, total, query.getLimit(), query.getPage());
		
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
		InsProductEntity insProduct = insProductService.queryObject(id);
		
		return R.ok().put("insProduct", insProduct);
	}
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "新增", notes = "")
	@ApiImplicitParam(name = "insProduct", value = "", required = true, dataType = "InsProductEntity")
	@PostMapping("/")
	public R save(@RequestBody InsProductEntity insProduct){
		insProductService.save(insProduct);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改", notes = "")
	@ApiImplicitParam(name = "insProduct", value = "", required = true, dataType = "InsProductEntity")
	@PutMapping("/")
	public R update(@RequestBody InsProductEntity insProduct){
		insProductService.update(insProduct);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除", notes = "")
	@ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
	@DeleteMapping("/")
	public R delete(@RequestBody Long[] ids){
		insProductService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
