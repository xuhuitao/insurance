package net.rokyinfo.insurance.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.Ebike;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.retrofit.RemoteService;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
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
    private OrderService orderService;

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

        //该用户所属保险公司
        query.put("belong", user.getBelong());

        List<String> orderCcuSnList = orderService.queryCcuSnOfOrder(query);
        List<Ebike> ebikeList = remoteService.getEbikeList(orderCcuSnList);;
        int total = orderService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ebikeList, total, query.getLimit(), query.getPage());
        return new R<>(pageUtil);
    }

}
