package net.rokyinfo.insurance.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
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
@RequestMapping("/v1.0/users")
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
    @GetMapping("")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserEntity> userList = userService.queryList(query);
        int total = userService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return new R<>(pageUtil);
    }


    /**
     * 详情
     */
    @ApiIgnore
    @ApiOperation(value = "详情", notes = "")
    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/{id}")
    public R info(@PathVariable("id") Long id) {
        UserEntity user = userService.queryObject(id);

        return new R<>(user);
    }

    /**
     * 当前登录用户详细
     */
    @GetMapping("/detail")
    public R detail() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.queryUserByUserName(token.getPrincipal().toString());

        if (user == null) {
            throw new RkException("不存在该用户");
        }

        return new R<>(user);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增", notes = "")
    @ApiImplicitParam(name = "user", value = "", required = true, dataType = "UserEntity")
    @PostMapping("")
    public R save(@RequestBody UserEntity user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword().trim()));
        userService.save(user);

        return new R<>();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", notes = "")
    @ApiImplicitParam(name = "user", value = "", required = true, dataType = "UserEntity")
    @PutMapping("")
    public R update(@RequestBody UserEntity user) {
        userService.update(user);

        return new R<>();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "")
    @ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
    @DeleteMapping("")
    public R delete(@RequestBody Long[] ids) {
        userService.deleteBatch(ids);

        return new R<>();
    }

}
