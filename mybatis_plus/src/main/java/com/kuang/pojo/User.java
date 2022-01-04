package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 郭宇航
 * @date 2022/1/4
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version //乐观锁注解
    private Integer version;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

}
