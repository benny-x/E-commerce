package com.imooc.service.center;

import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.center.OrderStatusCountsVO;
import com.imooc.utils.PagedGridResult;

public interface MyOrdersService {

     /**
      * 根据订单状态查询用户订单
      * @param userId
      * @param orderStatus
      * @param page
      * @param pageSize
      * @return
      */
     PagedGridResult queryMyOrders(String userId, Integer orderStatus,
                                   Integer page, Integer pageSize);

     /**
      * 根据订单Id查询用户的某一订单
      * @param userId
      * @param orderId
      * @return
      */
     Orders queryMyOrder(String userId, String orderId);

     /**
      * 更新订单状态 --> 商家发货
      * @param orderId
      */
     void updateDeliverOrderStatus(String orderId);

     /**
      * 更新订单状态 --> 确认发货
      * @param orderId
      * @return
      */
     boolean updateReceiveOrderStatus(String orderId);

     /**
      * 删除订单（逻辑删除）
      * @param userId
      * @param orderId
      * @return
      */
     boolean deleteOrder(String userId, String orderId);

     /**
      * 查询用户订单数
      * @param userId
      */
     OrderStatusCountsVO getMyOrdersStatusCounts(String userId);

     /**
      * 获得分页的订单动向
      * @param userId
      * @param page
      * @param pageSize
      * @return
      */
     PagedGridResult getMyOrderTrend(String userId, Integer page, Integer pageSize);

}
