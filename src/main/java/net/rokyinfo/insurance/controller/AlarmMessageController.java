package net.rokyinfo.insurance.controller;

import java.util.List;
import java.util.Map;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import net.rokyinfo.insurance.entity.AlarmMessageEntity;
import net.rokyinfo.insurance.service.AlarmMessageService;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 告警消息表
 * 
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-10 23:00:14
 */
@RestController
@RequestMapping("/v1.0/alarmmessage")
public class AlarmMessageController {
	@Autowired
	private AlarmMessageService alarmMessageService;

	@Autowired
	private UserService userService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "消息列表", notes = "")
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

		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		UserEntity user = userService.queryUserByUserName(token.getPrincipal().toString());
		query.put("belong", user.getBelong());

		List<AlarmMessageEntity> alarmMessageList = alarmMessageService.queryList(query);
		int total = alarmMessageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(alarmMessageList, total, query.getLimit(), query.getPage());

		return new R<>(pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ApiIgnore
	@ApiOperation(value = "详情", notes = "")
	@ApiImplicitParam(name = "id", value = "", required = true, dataType = "Integer", paramType = "path")
	@GetMapping("/{id}")
	public R info(@PathVariable("id") Long id){
		AlarmMessageEntity alarmMessage = alarmMessageService.queryObject(id);
		return new R<>(alarmMessage);
	}
	
	/**
	 * 新增
	 */
	@ApiOperation(value = "新增", notes = "")
	@ApiImplicitParam(name = "alarmMessageEntity", value = "", required = true, dataType = "AlarmMessageEntity")
	@PostMapping("")
	public R save(@RequestBody AlarmMessageEntity alarmMessage){
		alarmMessageService.save(alarmMessage);
		return new R<>();
	}
	
	/**
	 * 修改
	 */
	@ApiOperation(value = "修改", notes = "")
	@ApiImplicitParam(name = "alarmMessageEntity", value = "", required = true, dataType = "AlarmMessageEntity")
	@PutMapping("")
	public R update(@RequestBody AlarmMessageEntity alarmMessage){
		alarmMessageService.update(alarmMessage);
		return new R<>();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除", notes = "")
	@ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
	@DeleteMapping("")
	public R delete(@RequestBody Long[] ids){
		alarmMessageService.deleteBatch(ids);
		return new R<>();
	}
	
}
