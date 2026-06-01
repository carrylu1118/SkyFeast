package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    /**
     * 新增套餐
     * @param setmealDTO 套餐信息
     */
    @PostMapping
    @CacheEvict(value = "setmealCache", key = "setmealDTO.categoryId")
    public Result<Void> save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}", setmealDTO);
        setMealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO 套餐分页数据
     * @return 套餐分页数据
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除套餐
     * @param ids 套餐id数组
     * @return 删除结果
     */
    @DeleteMapping
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<Void> delete(@RequestParam List<Long> ids){
        setMealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id查询套餐，用于回显数据
     */
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setMealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐
     */
    @PutMapping
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<Void> update(@RequestBody SetmealDTO setmealDTO) {
        setMealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐起售、停售
     */
    @PostMapping("/status/{status}")
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<Void> startOrStop(@PathVariable Integer status, Long id) {
        setMealService.startOrStop(status, id);
        return Result.success();
    }
}
