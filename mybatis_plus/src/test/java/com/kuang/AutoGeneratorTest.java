package com.kuang;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * @author 郭宇航
 * @date 2022/1/4
 * @apiNote
 */
public class AutoGeneratorTest {

    public static void main(String[] args) {
        //需要一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 配置策略
        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取用户目录
        String property = System.getProperty("user.dir");
        gc.setOutputDir(property + "/src/main/java");
        gc.setAuthor("gyh");
        // 是否打开文件管理器
        gc.setOpen(false);
        // 是否覆盖
        gc.setFileOverride(true);
        // 去除service的I前缀
        gc.setServiceName("%sService");
        // 设置id策略
        gc.setIdType(IdType.ID_WORKER);
        // 设置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("root");
        dsc.setPassword("1234");
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //设置包
        PackageConfig packageConfig = new PackageConfig();
        // 模块名
        packageConfig.setModuleName("blog");
        // 父包名
        packageConfig.setParent("com.kuang");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("dao");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置要映射的表名
        strategyConfig.setInclude("user");
        //逻辑删除
        strategyConfig.setLogicDeleteFieldName("deleted");
        //驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategyConfig.setSuperEntityClass("你自己父类的实体，没有不要设置");
        // lombok
        strategyConfig.setEntityLombokModel(true);
        //自动填充配置
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        strategyConfig.setTableFillList(Arrays.asList(create_time, update_time));
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        // 开启restful驼峰明明格式
        strategyConfig.setRestControllerStyle(true);
        // 开启localhost:8080/hello_id_2格式
        strategyConfig.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategyConfig);

        mpg.execute();
    }
}
