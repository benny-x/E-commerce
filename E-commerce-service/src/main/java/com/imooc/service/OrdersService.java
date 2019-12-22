package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;


public interface OrdersService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    String createOrder(SubmitOrderBO submitOrderBO);

}
