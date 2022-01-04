package com.kuang;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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

    @Test
    void contextLoads4() {
        // 测试乐观锁成功！
        // 1.查询用户信息
        User user = userMapper.selectById(1);
        // 2.修改
        user.setName("123123");
        // 3,执行更新，参数是一个对象
        int updateById = userMapper.updateById(user);
        System.out.println(updateById);
        System.out.println(user);
    }

    @Test
    void contextLoads5() {
        // 测试乐观锁失败！
        // 线程1
        // 1.1.查询用户信息
        User user = userMapper.selectById(1);
        // 1.2.修改
        user.setName("123123111111111");

        // 线程2
        // 2.1.查询用户信息
        User user2 = userMapper.selectById(1);
        // 2.2.修改
        user2.setName("123123222222222");
        // 2.3.执行更新第二个线程，插队了抢先线程1更新
        userMapper.updateById(user2);

        // 1.3.执行更新第一个线程的。如果没有乐观锁就会覆盖插队线程的数据。
        // 实现自旋锁实现多次尝试! JUC课程
        userMapper.updateById(user);
    }

    @Test
    void contextLoads6() {
        // 查询单个操作
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    void contextLoads7() {
        // 查询多个操作
        // SELECT id,name,age,email,version,create_time FROM user WHERE id IN ( ? , ? , ? )
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    void contextLoads8() {
        // 多条件查询map,复杂的会用mapper操作
        // SELECT id,name,age,email,version,create_time FROM user WHERE name = ?
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        List<User> users = userMapper.selectByMap(map);
    }

    @Test
    void contextLoads9() {
        // 分页查询,参数1：当前页，参数2：页面大小
        // SELECT id,name,age,email,version,create_time FROM user LIMIT 10,10
        Page page = new Page(2,10);
        IPage selectPage = userMapper.selectPage(page, null);
        // 返回数据
        selectPage.getRecords().forEach(System.out::println);
        // 总数
        System.out.println(selectPage.getTotal());
    }

    @Test
    void contextLoads10() {
        // 删除操作,id删除
        //userMapper.deleteById(1478278183120453634L);
        // 批量删除,list
        //userMapper.deleteBatchIds(Arrays.asList(1478268198860804099L,1478275780337917954L));
        // 条件删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "测试");
        userMapper.deleteByMap(map);
    }

    @Test
    void contextLoads11() {
        // 逻辑删除,执行的是更新操作
        userMapper.deleteById(1L);
    }
}
