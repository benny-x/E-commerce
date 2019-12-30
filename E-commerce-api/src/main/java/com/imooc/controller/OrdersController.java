package com.imooc.controller;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayMethodEnum;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.OrdersService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Api(value = "订单", tags = {"创建订单"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    @Autowired
    private OrdersService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "提交订单", httpMethod = "POST", notes = "用户下单后，系统生成订单并发送给支付中心；支付中心订单联调接口：" +
                                    "http://payment.t.mukewang.com/foodie-payment/payment/getPaymentCenterOrderInfo?" +
                                    "merchantOrderId={订单id}&merchantUserId={用户id}")
    @PostMapping("/create")
    public IMOOCJSONResult create (@RequestBody SubmitOrderBO submitOrderBO) {

        if (submitOrderBO.getPayMethod() != PayMethodEnum.WEIXIN.type
         && submitOrderBO.getPayMethod() != PayMethodEnum.ALIPAY.type) {
            return IMOOCJSONResult.errorMsg("支付方式不支持!");
        }

//        System.out.println(submitOrderBO.toString());

        // 1. 创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();

        // 2. 创建订单以后, 移除购物车中已结算 (已提交) 的商品

        // TODO 整合redis之后, 完善购物车中的已结算商品清除,并且同步到前端的cookie
//        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(fileUpload.getPaymentReturnUrl());

        //为了方便测试付款，所有订单的支付金额统一设置为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","8204096-630402338");
        headers.add("password","dwkp-0ele-1m3n-0emn");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<IMOOCJSONResult> responseEntity = restTemplate.postForEntity(paymentUrl,
                                                                                    entity,
                                                                                    IMOOCJSONResult.class);
        IMOOCJSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            return IMOOCJSONResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return IMOOCJSONResult.ok(orderId);
    }

    @ApiOperation(value = "支付中心回调", notes = "支付成功后，提供给支付中心的回调方法，修改状态:已付款待发货", httpMethod = "POST")
    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid (String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @ApiOperation(value = "验证支付结果", notes = "支付界面循环调用的接口，验证订单已是否付款", httpMethod = "POST")
    @PostMapping("getPaidOrderInfo")
    public IMOOCJSONResult getPaidOrderInfo (String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return IMOOCJSONResult.ok(orderStatus);
    }

}
