package com.imooc.service.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

public interface CenterUserService {

     /**
      * 根据用户id查询用户信息
      * @param UserId
      * @return
      */
     Users queryUserInfo(String UserId);

     /**
      * 修改用户信息
      * @param userId
      * @param centerUserBO
      */
     Users updateUserInfo(String  userId, CenterUserBO centerUserBO);

     /**
      * 用户头像更新
      * @param userId
      * @param faceUrl
      * @return
      */
     Users updateUserFace(String userId, String faceUrl);

}
