package net.rokyinfo.insurance.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.rokyinfo.insurance.entity.Excel;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.entity.UserEntity;
import net.rokyinfo.insurance.json.JSON;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.service.UserService;
import net.rokyinfo.insurance.util.PageUtils;
import net.rokyinfo.insurance.util.Query;
import net.rokyinfo.insurance.util.R;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 保险订单表
 *
 * @author yangyang.cao
 * @email yangyang.cao@gmail.com
 * @date 2018-01-08 10:31:21
 */
@RestController
@RequestMapping("/v1.0/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation(value = "保险订单列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "订单状态", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "分页第几页示例：1", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "分页每页总数示例：20", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sidx", value = "排序字段：id", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序顺序：desc", required = false, dataType = "String", paramType = "query")}
    )
    @GetMapping("")
    @JSON(type = OrderEntity.class, filter = "billFile,scooterFiles,applicantExcel,idTypeExcel,idNumberExcel,phoneNumberExcel,createTimeExcel,vinExcel,ccuSnExcel,brandExcel,modelExcel,buyPriceExcel,buyTimeExcel,coverageExcel")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        Object statusParams = query.get("status");
        if (statusParams != null) {
            String[] statusArray = statusParams.toString().split(",");
            query.put("status", statusArray);
        }
        List<OrderEntity> insOrderList = orderService.queryListByStatusArray(query);
        int total = orderService.queryTotalByStatusArray(query);

        PageUtils pageUtil = new PageUtils(insOrderList, total, query.getLimit(), query.getPage());

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
        OrderEntity insOrder = orderService.queryObject(id);
        return new R<>(insOrder);
    }

    @ApiOperation(value = "审批", notes = "")
    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "Integer", paramType = "path")
    @PutMapping("/{id}")
    public R affirm(@PathVariable("id") Long id, @RequestParam Integer dispose) {
        orderService.affirm(id, dispose);
        return new R<>();
    }

    @GetMapping("/excel")
    public R exportExcel(@RequestParam(required = false) Integer status, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.queryUserByUserName(token.getPrincipal().toString());
        Map<String, Object> params = new HashMap<>(2);
        params.put("belong", user.getBelong());
        if (status != null) {
            params.put("status", status);
        }

        Excel excel = new Excel();
        excel.setUrl(orderService.generateExcel(params));
        return new R<>(excel);

    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增", notes = "")
    @ApiImplicitParam(name = "insOrder", value = "", required = true, dataType = "OrderEntity")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public R save(@Valid @ModelAttribute OrderEntity insOrder) throws IOException, ParseException {
        return orderService.save(insOrder, insOrder.getBillFile(), insOrder.getScooterFiles());
    }

    /**
     * 获取支付信息
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "获取支付信息", notes = "")
    @RequestMapping(value = "/payinfo-web", method = RequestMethod.GET)
    public R payInfo(@RequestParam String orderNo) throws IOException {
        return orderService.payInfo(orderNo);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", notes = "")
    @ApiImplicitParam(name = "insOrder", value = "", required = true, dataType = "OrderEntity")
    @PutMapping("/update")
    public R update(@RequestParam String ccuSn, @RequestParam String orderNo) throws IOException, ParseException {
        orderService.update(ccuSn, orderNo);
        return new R<>();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "")
    @ApiImplicitParam(name = "ids", value = "", required = true, dataType = "Long[]")
    @DeleteMapping("")
    public R delete(@RequestBody Long[] ids) {
        orderService.deleteBatch(ids);
        return new R<>();
    }

}
