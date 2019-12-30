package com.imooc.controller;


import com.imooc.pojo.Orders;
import com.imooc.pojo.Users;
import com.imooc.resource.FileUpload;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

//    protected static final String FOODIE_SHOPCART = "shopcart";

    protected static final Integer COMMON_PAGE_NUMBER = 1;
    protected static final Integer COMMON_PAGE_SIZE = 10;
    protected static final Integer SEARCH_PAGE_SIZE = 20;

    // 支付中心的调用地址
    protected static final String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    @Autowired
    protected FileUpload fileUpload;

    @Autowired
    private MyOrdersService myOrdersService;

    // 验证用户和订单是否有关联关系，避免非法用户调用
    public IMOOCJSONResult checkUserOrder (String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return IMOOCJSONResult.errorMsg("订单不存在！");
        }
        return IMOOCJSONResult.ok(order);
    }

    // 返回给前端的用户对象：清除隐私和不必要信息
    public Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
