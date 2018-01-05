package net.rokyinfo.insurance.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 用户表
 * 
 * @author zhijian.yuan
 * @email zhijian.yuan@gmail.com
 * @date 2018-01-05 13:18:54
 */
@RestController
@RequestMapping("/insurance/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "列表", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
	)
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserEntity> userList = userService.queryList(query);
		int total = userService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		UserEntity user = userService.queryObject(id);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public R save(@RequestBody UserEntity user){
		userService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@PutMapping("/update")
	public R update(@RequestBody UserEntity user){
		userService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		userService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
