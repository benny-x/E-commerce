package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.center.OrderStatusCountsVO;
import com.imooc.service.center.CenterUserService;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "用户中心-首页", tags = {"用户中心"})
@RestController
@RequestMapping("center")
public class CenterController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private MyOrdersService myOrdersService;
    
    @ApiOperation(value = "获取用户信息", notes = "查询用户信息", httpMethod = "GET")
    @GetMapping("userInfo")
    private IMOOCJSONResult userInfo (
                        @ApiParam(name = "userId", value = "用户id", required = true)
                        @RequestParam  String userId) {

        Users user = centerUserService.queryUserInfo(userId);
        return IMOOCJSONResult.ok(user);

    }

    @ApiOperation(value = "获取订单状态概览数量", notes = "查询订单状态概览数量", httpMethod = "POST")
    @PostMapping("/statusCounts")
    private IMOOCJSONResult statusCounts (
                        @ApiParam(name = "userId", value = "用户id", required = true)
                        @RequestParam String userId) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        OrderStatusCountsVO result = myOrdersService.getMyOrdersStatusCounts(userId);

        return IMOOCJSONResult.ok(result);
    }

    @ApiOperation(value = "查询订单动向", notes = "查询订单动向", httpMethod = "POST")
    @PostMapping("/trend")
    private IMOOCJSONResult trend (
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = COMMON_PAGE_NUMBER;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myOrdersService.getMyOrderTrend(userId, page, pageSize);

        return IMOOCJSONResult.ok(grid);
    }

}
