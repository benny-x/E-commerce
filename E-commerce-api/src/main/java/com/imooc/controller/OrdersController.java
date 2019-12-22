package com.imooc.controller;

import com.imooc.enums.PayMethodEnum;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.OrdersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Autowired
    private OrdersService orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create (@RequestBody SubmitOrderBO submitOrderBO,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        if (submitOrderBO.getPayMethod() != PayMethodEnum.WEIXIN.type
         && submitOrderBO.getPayMethod() != PayMethodEnum.ALIPAY.type) {
            return IMOOCJSONResult.errorMsg("支付方式不支持!");
        }

        System.out.println(submitOrderBO.toString());

        // 1. 创建订单
        String orderId = orderService.createOrder(submitOrderBO);

        // 2. 创建订单以后, 移除购物车中已结算 (已提交) 的商品

        // TODO 整合redis之后, 完善购物车中的已结算商品清除,并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据

        return IMOOCJSONResult.ok(orderId);
    }

}
