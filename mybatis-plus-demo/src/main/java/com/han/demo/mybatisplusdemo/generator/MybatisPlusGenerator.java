package com.han.demo.mybatisplusdemo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public final class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 项目路径
        String projectPath = "F:\\project\\springboot-demo\\mybatis-plus-demo";
        // 数据库名
        String database = "test";
        // 包名
        String parentPackage = "com.han.demo.mybatisplusdemo";
        // 表名前缀
        String tablePrefix = "t_";
        // 表集合
        String[] tables = {"t_user"};

        MybatisPlusGenerator mybatisPlusGenerator = new MybatisPlusGenerator();
        mybatisPlusGenerator.generator(projectPath, database, parentPackage, tablePrefix, tables);
    }

    /**
     * 整合配置
     */
    private void generator(String projectPath, String database, String parentPackage, String tablePrefix, String[] tables) {
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig(projectPath))
                .setDataSource(dataSourceConfig(database))
                .setPackageInfo(packageConfig(parentPackage))
                .setStrategy(strategyConfig(tablePrefix, tables))
                .setCfg(injectionConfig(projectPath))
                .setTemplate(templateConfig());
        autoGenerator.execute();
    }

    /**
     * 全局配置
     * @return GlobalConfig
     */
    private GlobalConfig globalConfig(String projectPath) {
        // 1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 作者
        gc.setAuthor("lizhihan");
        // 生成路径
        gc.setOutputDir(projectPath + "/src/main/java");
        // 主键策略
        gc.setIdType(IdType.AUTO);
        // 文件覆盖
        gc.setFileOverride(true);
        // 是否支持AR模式
        gc.setActiveRecord(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        return gc;
    }

    /**
     * 数据源配置
     * @return DataSourceConfig
     */
    private DataSourceConfig dataSourceConfig(String database) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/" + database.trim() + "?useUnicode=true&useSSL=false&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("123456");
        return dsc;
    }

    /**
     * 包名策略配置
     * @return PackageConfig
     */
    private PackageConfig packageConfig(String parentPackage) {
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage)
                .setEntity("bean")
                .setMapper("dao")
                .setXml("mapper");
        return pc;
    }

    /**
     * 策略配置
     * @return StrategyConfig
     */
    private StrategyConfig strategyConfig(String tablePrefix, String[] tables) {
        StrategyConfig sc = new StrategyConfig();
        sc.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntityLombokModel(true)
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(tablePrefix)
                .setInclude(tables);
        return sc;
    }

    /**
     * 自定义配置
     * @return InjectionConfig
     */
    private InjectionConfig injectionConfig(String projectPath) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

    /**
     * 模板配置
     * @return TemplateConfig
     */
    private TemplateConfig templateConfig() {
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        return tc;
    }
}
