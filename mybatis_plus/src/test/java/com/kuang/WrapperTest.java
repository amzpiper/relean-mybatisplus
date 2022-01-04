package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author 郭宇航
 * @date 2022/1/4
 * @apiNote
 */
@SpringBootTest
public class WrapperTest {
    /**
     * 基础了BaseMapper，所有的方法都来自父类
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询复杂的，name不为空，邮箱不为空的数据，年龄大于等于12岁
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 类似map,map直接put，这个使用方法
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void contextLoads2() {
        // 查询一个数据selectOne,名字为Tom,多个时使用List或Map
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Tom");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void contextLoads3() {
        // 查询年龄再20~30岁用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        int count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    void contextLoads4() {
        // 模糊查询，名字中不包括a的。并且邮箱以t开头的数据。
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右代表%在左还是右
        wrapper.notLike("name", "a").likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void contextLoads5() {
        // 内查询：id小于3
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 3");
        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    void contextLoads6() {
        // 降序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

}
