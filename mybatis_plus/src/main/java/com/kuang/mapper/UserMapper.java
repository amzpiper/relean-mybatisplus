package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 郭宇航
 * @date 2022/1/4
 * @apiNote 使用mybatis-plus后,只需要实现基本的接口BaseMapper
 */
// Repository表示为dao层的就可以了
@Repository
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD都已经自动生成了。
    //不需要像以前那样配置一大堆文件里
}
