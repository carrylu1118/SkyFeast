package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid 用户id
     * @return 用户信息
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user 用户信息
     */
    void insert(User user);

    /**
     * 根据id查询用户
     * @param userId 用户id
     * @return 用户信息
     */
    @Select("select * from user where id = #{id}")
    User getById(Long userId);

    /**
     * 根据条件统计用户数量
     */
    Integer countByMap(Object o);
}
