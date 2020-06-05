package me.jmix.brothertakeaway.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.entity.OrderDetail;
import me.jmix.brothertakeaway.enums.OrderEnum;
import me.jmix.brothertakeaway.exception.OrderException;
import me.jmix.brothertakeaway.form.CustomerOrderForm;

import java.util.List;

@Slf4j
public class OrderFormToOrderDTOConverter {
    public static OrderDTO convert(CustomerOrderForm customerOrderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCustomerName(customerOrderForm.getName());
        orderDTO.setCustomerPhone(customerOrderForm.getPhone());
        orderDTO.setCustomerAddress(customerOrderForm.getAddress());
        orderDTO.setCustomerOpenid(customerOrderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(customerOrderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("[JSON转换]错误，string = {}", customerOrderForm.getItems());
            throw new OrderException(OrderEnum.JSON_CONVERT_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
