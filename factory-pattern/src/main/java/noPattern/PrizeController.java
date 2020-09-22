package noPattern;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import service.card.IQiYiCardService;
import service.coupon.CouponResult;
import service.coupon.CouponService;
import service.goods.DeliverReq;
import service.goods.GoodsService;

@Slf4j
public class PrizeController {
    public AwardRes awardToUser(AwardReq req) {
        String reqJson = JSON.toJSONString(req);
        AwardRes awardRes = null;
        try {
            log.info("奖品发放开始,req = {},{}", req.getUId(), reqJson);
            // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
            if (req.getAwardType() == 1) {
                CouponService couponService = new CouponService();
                CouponResult couponResult = couponService.sendCoupon(req.getUId(), req.getAwardNumber(), req.getBizId());
                if ("0000".equals(couponResult.getCode())) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", couponResult.getInfo());
                }
            } else if (req.getAwardType() == 2) {
                GoodsService goodsService = new GoodsService();
                DeliverReq deliverReq = DeliverReq.builder()
                        .userName(queryUserName(req.getUId()))
                        .userPhone(queryUserPhoneNumber(req.getUId()))
                        .sku(req.getAwardNumber())
                        .orderId(req.getBizId())
                        .consigneeUserName(req.getExtMap().get("consigneeUserName"))
                        .consigneeUserAddress(req.getExtMap().get("consigneeUserPhone"))
                        .consigneeUserAddress(req.getExtMap().get("consigneeUserAddress"))
                        .build();
                Boolean isSuccess = goodsService.deliverGoods(deliverReq);
                if (isSuccess) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", "发放失败");
                }
            } else if (req.getAwardType() == 3) {
                IQiYiCardService iQiYiCardService = new IQiYiCardService();
                iQiYiCardService.grantToken(queryUserPhoneNumber(req.getUId()), req.getAwardNumber());
                awardRes = new AwardRes("0000", "发放成功");
            }
            log.info("奖品发放完成{}", req.getUId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return awardRes;
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
