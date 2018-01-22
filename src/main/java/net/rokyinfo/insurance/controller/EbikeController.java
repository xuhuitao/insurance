package net.rokyinfo.insurance.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.Ebike;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.enums.OrderStatus;
import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.retrofit.RemoteService;
import net.rokyinfo.insurance.service.EbikeService;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1.0/ebike")
public class EbikeController {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private UserService userService;

    @Autowired
    private EbikeService ebikeService;

    /**
     * 列表
     */
    @ApiOperation(value = "车辆列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
    )
    @GetMapping("")
    public R list(@RequestParam Map<String, Object> params) throws IOException {
        //查询列表数据
        Query query = new Query(params);

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.queryUserByUserName(token.getPrincipal().toString());

        if (user == null) {
            throw new RkException("不存在该用户");
        }

        //该用户所属保险公司
        query.put("belong", user.getBelong());
        //只查询出保障中的保险订单
        query.put("status", OrderStatus.IN_INSURANCE.getOrderStatusValue());

        List<Ebike> ebikeList = ebikeService.queryList(query);
        int total = ebikeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ebikeList, total, query.getLimit(), query.getPage());
        return new R<>(pageUtil);
    }

    @ApiOperation(value = "详情", notes = "")
    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "path")
    @GetMapping("/detail")
    public R info(@RequestParam("ccuSn") String ccuSn) throws IOException {
        if (TextUtils.isEmpty(ccuSn)) {
            throw new RkException("请指定要查询的中控序列号");
        }
        List<String> ccuSnList = new ArrayList<>();
        ccuSnList.add(ccuSn);
        List<Ebike> ebikeList = remoteService.getEbikeList(ccuSnList);;
        return new R<>(ebikeList);
    }

}
