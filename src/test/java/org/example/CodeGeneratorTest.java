package org.example;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.junit.Test;

import java.util.Collections;

public class CodeGeneratorTest {

    @Test
    public void testSimple() {

        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/translation", "root", "Root@159")
                .schema("translation")
                .dbQuery(new MySqlQuery())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                .author("FrankCheng")
                .disableOpenDir()
                .enableKotlin()
                .enableSwagger()
                .dateType(DateType.ONLY_DATE)
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com")
                .moduleName("little")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .controller("controller")
                .xml("mapper")
                .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/src/main/resources/mapper"))
                .build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude("miao", "who", "users") //选择需要映射的表名
                .build();

        StrategyConfig entityConfig = strategyConfig
                .entityBuilder()
                .logicDeleteColumnName("deleted")
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .enableLombok()
                .versionColumnName("version")
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                .build();

        StrategyConfig controlConfig = entityConfig
                .controllerBuilder()
                .enableRestStyle()
                .enableHyphenStyle()
                .formatFileName("%sController")
                .build();

        new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .packageInfo(packageConfig)
                .strategy(controlConfig)
                .execute();
    }
}
