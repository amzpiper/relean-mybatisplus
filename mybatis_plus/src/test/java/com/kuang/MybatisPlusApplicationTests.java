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

    @Test
    void contextLoads2() {
        User user = new User();
        user.setName("测试2");
        user.setAge(3);
        user.setEmail("15512702732@163.com");
        // 插入
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);

        // 帮我们自动生产了id，并且id会自动回填到user对象中。
    }

    @Test
    void contextLoads3() {
        User user = new User();
        user.setId(1478268198860804099L);
        user.setName("测试");
        user.setAge(3);
        user.setEmail("15512702732@163.com");
        // 更新，参数是一个对象
        int insert = userMapper.updateById(user);
        System.out.println(insert);
        System.out.println(user);
    }

}
