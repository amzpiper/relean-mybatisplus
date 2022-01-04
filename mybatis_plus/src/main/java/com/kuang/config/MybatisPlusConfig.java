package com.kuang.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 郭宇航
 * @date 2022/1/4
 * @apiNote
 */
//扫描mapper接口
@MapperScan("com.kuang.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后的操作，true调回首页，false继续请求，默认false
        paginationInterceptor.setOverflow(false);
        return paginationInterceptor;
    }

    // 逻辑删除
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    // 性能分析插件,只有在dev和test环境下开启，为了保证效率
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置sql能够执行的最大时间，如果超过了则不执行。比如：1ms
        // 一般设置为100
        performanceInterceptor.setMaxTime(100);
        // sql格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

}
