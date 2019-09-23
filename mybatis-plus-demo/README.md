# Mybatis-Plus使用Demo
### 代码生成器
> [官方教程](https://mp.baomidou.com/guide/generator.html)  
  [配置说明](https://mp.baomidou.com/config/generator-config.html)  
1. 引入依赖
    ```xml
    <dependencys>
        <!-- 代码生成器 依赖 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
        <!-- 模板引擎 依赖 -->
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.1</version>
        </dependency>
        <!-- mysql 依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
        </dependency>
    </dependencys>
    ```
2. 编写配置文件  
[java代码](./src/main/java/com/han/demo/mybatisplusdemo/generator/MybatisPlusGenerator.java)
    
3. 插件使用  
[java代码](./src/main/java/com/han/demo/mybatisplusdemo/MybatisPlusConfig.java)