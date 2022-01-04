package com.kuang;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    /**
     * 基础了BaseMapper，所有的方法都来自父类
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询全部用户
        // 参数是一个wapper，一个条件构造器
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
