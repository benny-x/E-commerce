package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemID
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemID);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价(分页)
     * @param itemId
     * @param level
     * @return
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    /**
     * 搜索商品列表(根据关键字)
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItemsByKeywords(String keywords, String sort,
                                        Integer page, Integer pageSize);

    /**
     * 搜索商品列表(根据分类id)
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItemsByThirdCat(Integer catId, String sort,
                                        Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据 (用于刷新渲染购物车中的商品数据)
     * @param specIds
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获得商品主图url
     * @param itemId
     * @return
     */
    String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    void decreaseItemSpecStock(String specId, int buyCounts);

}
