package com.spiderman.example.config;

/**
 * Created by Administrator on 2017/11/28 0028.
 * 所有请求都需要携带token
 */

public class HttpAddress {

    public static final String mBaseUrl = "http://www.syb111.com/"; //商城网址
    /**
     * JS
     */
    public static final String userAppMessage = "app/mall/message/userAppMessage.htm";   //获取系统消息
    public static final String createBrandOrder = "app/mall/order/createBrandOrder.htm";    //创建品牌大促订单

    public static final String getFeeByGCID = "app/mall/order/getFeeByGCID.htm";   //订单费用计算

    public static final String healthArticleList = "app/mall/article/healthArticleList.htm";

    public static final String lifeArticleList = "app/mall/article/lifeArticleList.htm";

    public static final String GoodList = "app/mall/mall/getGoodsList.htm";

    public static final String goodClassList = "app/mall/mall/getGoodsClassList.htm";

    public static final String index = "app/mall/mall/index.htm";   //首页

    public static final String goodsDetail = "app/mall/mall/goodsDetail.htm";

    public static final String getFiveWholesaleOrderList = "/app/mall/wholesale/getFiveWholesaleOrderList.htm";

    public static final String getAddressList = "app/mall/address/getAddressList.htm";  //用户地址列表

    public static final String addAddress = "app/mall/address/addAddress.htm";  //新增用户地址

    public static final String getAddressDetail = "app/mall/address/getAddressDetail.htm";  //查询用户地址信息

    public static final String updateAddress = "app/mall/address/updateAddress.htm";    //跟新用户地址

    public static final String getDefaultAddress = "app/mall/address/getDefaultAddress.htm";    //获取默认地址

    public static final String pay = "app/mall/pay/pay.htm";    //调起支付

    public static final String setDefaultAddress = "app/mall/address/setDefaultAddress.htm";    //设置默认地址

    public static final String deleteAddress = "app/mall/address/deleteAddress.htm"; //删除默认地址

    public static final String createOrderByGc = "app/mall/order/createOrderByGc.htm"; //通过子购物车编号集提交订单

    public static final String addToCart = "app/mall/order/addToCart.htm"; //添加商品到购物车

    public static final String addCollect = "app/mall/favorite/add.htm"; //收藏店铺或者商品

    public static final String getCollectList = "app/mall/favorite/selectFavoriteGoods.htm"; //收藏列表

    public static final String bigBrandList = "app/mall/brand/bigBrandList.htm";    //大促品牌列表

    public static final String storeClassList = "app/mall/brand/storeClassList.htm"; //店铺商品分类列表

    public static final String brandStoreList = "app/mall/brand/brandStoreList.htm"; //品牌大促商家列表

    public static final String storeBannerList = "app/mall/brand/storeBannerList.htm";    //店铺banner列表

    public static final String storeGoodsClassList = "app/mall/brand/storeGoodsClassList.htm";  //店铺商品分类

    public static final String storeGoodsList = "app/mall/brand/storeGoodsList.htm"; //店铺商品列表

    public static final String storeIndex = "app/mall/brand/storeIndex.htm";    //店铺首页

    public static final String checkUpdate = "app/mall/update/download.htm";  //检查更新

    public static final String removeGoodsCart = "app/mall/order/removeGoodsCart.htm";    //从购物车删除

    public static final String goods_cart1 = "app/mall/cart/goods_cart1.htm"; //查看购物车

    public static final String look_goodscart = "app/mall/order/getStoreCardList.htm";  //查看购物车

    public static final String goodsCountAdjust =  "app/mall/order/goodsCountAdjust.htm";   //调整购物车商品数量

    public static final String updateWrite = "app/mall/message/updateWrite.htm";  //标记为已读

    public static final String addChatRecord = "app/chatrecord/addChatRecord.htm";  //
    /**
     * LP
     */
    public static final String loginByWX = "app/mall/login/loginByWX.htm"; // 微信授权登陆

    public static final String home_sign = "app/mall/integral/home_sign.htm"; // 签到领钱

    public static final String oneMonthRecord = "app/mall/integral/oneMonthRecord.htm"; // 我的省券页面

    public static final String createFreeOrder = "app/mall/order/createFreeOrder.htm"; // 创建免单

    public static final String freeOrderList = "app/mall/free/freeOrderList.htm"; // 我的免单

    public static final String shareFreeOrder = "app/mall/order/shareFreeOrder.htm"; // 重新分享免单

    public static final String createInviteeFreeOrder = "app/mall/order/createInviteeFreeOrder.htm"; //  创建受邀者免单

    public static final String getPlanList = "app/mall/comparePrice/getPlanList.htm"; // 全网比价时间列表

    public static final String getPlanGoodsList = "app/mall/comparePrice/goodsList.htm"; // 全网比价商品列表

    public static final String createIntegralOrder = "app/mall/order/createIntegralOrder.htm"; // 创建积分订单

    public static final String sendBandPhoneNumberCode = "app/mall/login/sendBandPhoneNumberCode.htm"; // 发送绑定手机号验证码

    public static final String checkCode = "app/mall/login/checkCode.htm"; // 检验验证码

    public static final String bandPhoneNumber = "app/mall/login/bandPhoneNumber.htm"; // 绑定手机号

    public static final String createComparePriceOrder = "app/mall/order/createComparePriceOrder.htm"; // 创建全网比价订单

    public static final String selectFavoriteGoods = "app/mall/favorite/selectFavoriteGoods.htm"; // 收藏夹商品列表

    public static final String selectFavoriteStore = "app/mall/favorite/selectFavoriteStore.htm"; // 收藏夹店铺列表

    public static final String deleteFavorite = "app/mall/favorite/delete.htm"; // 删除收藏

    public static final String myCutPriceOrderList = "app/mall/cut/myCutPriceOrderList.htm"; // 我的砍单列表

    public static final String createCutPriceOrder = "app/mall/order/createCutPriceOrder.htm"; // 创建砍单

    public static final String createNolOrder = "app/mall/order/createOrder.htm"; // 普通支付

    public static final String shareCutPriceOrder = "app/mall/cut/shareCutPriceOrder.htm"; // 分享砍单链接

    public static final String payForCutPriceOrder = "app/mall/order/payForCutPriceOrder.htm"; // 砍单支付

    public static final String orderDetail = "app/mall/order/orderDetail.htm"; // 订单详情

    public static final String refundAll = "app/mall/refund/all.htm"; // 退款售后全部

    public static final String wait_confirmed = "app/mall/refund/wait_confirmed.htm"; // 退款售后全部

    public static final String award_goods = "app/mall/integral/award_goods.htm"; // 领取奖品和中奖纪录

    public static final String createAwardOrder = "app/mall/order/createAwardOrder.htm"; // 创建抽奖订单

    public static final String toEvaluate = "app/mall/order/toEvaluate.htm"; // 订单评价页面

    public static final String orderConfirmReceive = "app/mall/order/orderConfirmReceive.htm"; // 确认订单已收货

    public static final String orderEvaluate = "app/mall/order/orderEvaluate.htm"; // 订单评价

    public static final String deleteOrder = "app/mall/order/deleteOrder.htm"; // 删除订单

    public static final String cancelOrder = "app/mall/order/cancelOrder.htm"; // 待支付状态下才能取消订单

    public static final String orderLogistics = "app/mall/logistics/select.htm"; // 商品物流信息接口

    public static final String to_apply = "app/mall/return/to_apply.htm"; // 申请退货页面

    public static final String refundInit = "app/mall/refund/init.htm"; // 申请退货页面

    public static final String saveRefund = "app/mall/return/save.htm"; // 保存退货或者退款申请

    public static final String returnDetail = "app/mall/refund/refund_await.htm"; // 退货退款提示详情

    public static final String return_see = "app/mall/return/return_see.htm"; // 退货退款订单详情

    public static final String return_logistics = "app/mall/return/return_logistics.htm"; // 寄回商品填写物流

    public static final String save_logistics = "app/mall/return/save_logistics.htm"; // 保存退货物流信息

    public static final String logic_delete = "app/mall/refund/logic_delete.htm"; // 删除退货或者退款售后

    public static final String getFeeByOrderId = "app/mall/order/getFeeByOrderId.htm"; // 通过订单编号获取待付款费用

    public static final String payForOrder = "app/mall/order/payForOrder.htm"; // 待付款订单支付

    public static final String createNPNOrder = "app/mall/order/createNPNOrder.htm"; // 提交9.9包邮订单

    public static final String createCreativeOrder = "app/mall/order/createCreativeOrder.htm"; // 创建创意新品订单

    public static final String orderStatusCount = "app/mall/order/orderStatusCount.htm"; // 订单数量统计

    public static final String getFeeByGoodsId = "app/mall/order/getFeeByGoodsId.htm"; // 订单数量统计

    public static final String feedback = "app/mall/feedback/add.htm"; // 添加意见反馈接口

    public static final String goodsEvaluateList = "app/mall/mall/goodsEvaluateList.htm"; // 商品评价列表

    public static final String deleteFreeOrder = "app/mall/free/deleteOrder.htm"; // 删除免单

    public static final String notWriteNo = "app/mall/message/notWriteNo.htm"; // 未读消息数量

    public static final String checkTokenStatus = "app/mall/login/checkTokenStatus.htm"; // 检验token状态

    public static final String getEquipmen = "app/mall/equipment/obtain.htm"; // 获取用户设备信息保存

    public static final String init_award = "app/mall/integral/init_award.htm?token="; // 积分抽奖

    /**
     * DR
     */
    public static final String STATUS_SUCCESS = "0";

    //text
    //text  http://192.168.0.198:8003/fox/app/hs/couponproduct/getAppList.do
    //http://101.37.84.155:80/fox/app/hs/couponproduct/getAppList.do
    //猫劵一二级列表
    public static final String couponTypeList = "app/hs/couponproduct/getAppList.do";

    //猫劵列表
    public static final String couponList = "app/interFaceAppController/tbkGetItems";

    //京劵类目
    public static final String getJDTypeList = "app/interFaceAppController/getTypeList";

    //京劵列表
    public static final String getJdGoodsList = "app/interFaceAppController/getJdGoodsList";

    //京劵二合一转链接口
    public static final String getGoodsLink = "app/interFaceAppController/getGoodsLink";

    //猫劵二合一转链接口
    public static final String getCatLink = "app/interFaceAppController/getLink";

    //普通订单列表
    public static final String normalOrderList = "app/mall/order/orderList.htm";

}
