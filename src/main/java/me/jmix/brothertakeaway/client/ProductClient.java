package me.jmix.brothertakeaway.client;

import me.jmix.brothertakeaway.dto.CartDTO;
import me.jmix.brothertakeaway.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author JellyfishMIX
 * @date 2020/6/17 4:44 下午
 */
// name是应用的名字，表示要访问哪个应用的API
@FeignClient(name = "brother-takeaway-product")
public interface ProductClient {
    @GetMapping("/brother-takeaway-product/server/message")
    String productMsg();

    /**
     * 获取商品列表（给订单服务使用）
     *
     * @param productInfoList productInfoList
     * @return
     */
    @PostMapping("/brother-takeaway-product/product/list-for-order")
    List<ProductInfo> listForProduct(@RequestBody List<String> productInfoList);

    /**
     * 扣库存
     *
     * @param cartDTOList cartDTOList
     */
    @PostMapping("/brother-takeaway-product/product/decrease-stock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
