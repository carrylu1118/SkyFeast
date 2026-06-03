package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders 订单数据
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);


    /**
     * 分页条件查询并按下单时间排序
     * @param ordersPageQueryDTO 查询参数
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单数据
     * @param id 订单id
     */
    @ Select("select * from orders where id=#{id}")
    Orders getById(Long id);


    /**
     * 修改订单数据
     * @param orders 订单数据
     */
    void update(Orders orders);

    /**
     * 根据状态统计订单数量
     * @param status 状态
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 根据订单状态和下单时间查询订单
     * @param status 订单状态
     * @param orderTime 下单时间
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据动态条件查询数据
     * @param map 查询条件
     */
    Double sumByMap(Map map);

    /**
     * 根据动态条件查询订单数据
     * @param map 查询条件
     */
    Integer countByMap(Map map);

    /**
     * 查询销量排名top10
     * @param begin 开始时间
     * @param end   结束时间
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
