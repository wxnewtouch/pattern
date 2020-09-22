package hasPattern.store;

import com.alibaba.fastjson.JSON;
import hasPattern.ICommodity;
import lombok.extern.slf4j.Slf4j;

import service.goods.DeliverReq;
import service.goods.GoodsService;

import java.util.Map;
@Slf4j
public class GoodsCommodityService implements ICommodity {

    private GoodsService goodsService = new GoodsService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        DeliverReq deliverReq = DeliverReq.builder()
                .userName(queryUserName(uId))
                .userPhone(queryUserPhoneNumber(uId))
                .sku(commodityId)
                .orderId(bizId)
                .consigneeUserName(extMap.get("consigneeUserName"))
                .consigneeUserAddress(extMap.get("consigneeUserPhone"))
                .consigneeUserAddress(extMap.get("consigneeUserAddress"))
                .build();
        Boolean isSuccess = goodsService.deliverGoods(deliverReq);

        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", isSuccess);

        if (!isSuccess) throw new RuntimeException("实物商品发放失败");
    }


    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
