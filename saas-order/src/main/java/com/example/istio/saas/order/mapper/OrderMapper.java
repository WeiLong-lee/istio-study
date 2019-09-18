package com.example.istio.saas.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.istio.saas.order.entity.Item;
import com.example.istio.saas.order.entity.Order;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

  @Select("select * from item left join orders_items oi on oi.items_id = item.id and oi.order_id =#{orderId} ")
  List<Item> selectItemsByOrderId(Long orderId);
}
