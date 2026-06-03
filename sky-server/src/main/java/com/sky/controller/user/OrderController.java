package com.sky.controller.user;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/order")
@RestController(value = "userOrderController")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户下单,参数为：{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 查看历史订单
     * @param page     页面
     * @param pageSize 每页记录数
     * @param status   订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     */
    @GetMapping("/historyOrders")
    public Result<PageResult> historyOrders(int page, int pageSize, Integer status) {
        log.info("查看历史订单...");
        PageResult pageResult = orderService.pageQuery4User(page, pageSize, status);
        return Result.success(pageResult);
    }

    /**
     * 查看订单详情
     * @param id 订单id
     */
    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> details(@PathVariable("id") Long id){
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 取消订单
     * @param id 订单id
     */
    @PutMapping("/cancel/{id}")
    public Result<Void> cancel(@PathVariable("id") Long id) throws Exception{
        orderService.userCancelById(id);
        return Result.success();
    }

    /**
     * 用户再来一单
     * @param id 订单id
     */
    @PostMapping("/repetition/{id}")
    public Result<Void> repetition(@PathVariable("id") Long id){
        orderService.repetition(id);
        return Result.success();
    }
}
