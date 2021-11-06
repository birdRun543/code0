package com.birdRun543.code0;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.birdRun543.code0.common.util.BasicItemUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MyBatis-plus 代码生成器
 */
@Slf4j
public class CodeGenerator {

    // 1. 设置表名
    private static final String[] tableNames = {"table1"};
    // 2. 设置包名
    private static final String packageName = "com.birdRun543.code0";
    // 3. 设置yml路径
    private static final String PROPERTY_NAME = "application-dev.yml";


    // 4. 启动工具
    public static void main(String[] args) {
        generateByTables(tableNames);
    }


    private static final Properties properties = BasicItemUtil.initProperties(PROPERTY_NAME);

    public static Object getYml(Object key) {

        try {
            assert properties != null;
            return properties.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getYmlString(String key) {
        return (String) getYml(key);
    }


    private static void generateByTables(String... tableNames) {

        AutoGenerator mpg = new AutoGenerator();  // 代码生成器

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 输出位置
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);  // 覆盖文件
        gc.setAuthor(System.getProperty("user.name"));
        // 是否打开目录
        gc.setOpen(false);
        gc.setActiveRecord(false);  // ActiveRecord 模式
        gc.setEnableCache(false);  // 二级缓存

        gc.setServiceName("%sService");  // service命名方式
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(getYmlString("spring.datasource.url"));
        dsc.setDriverName(getYmlString("spring.datasource.driver-class-name"));
        dsc.setUsername(getYmlString("spring.datasource.username"));
        dsc.setPassword(getYmlString("spring.datasource.password"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(CodeGenerator.packageName);
        pc.setMapper("dao");
        pc.setController("controller");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // 如果需要设置不在package内生成xml,则set(null)
        templateConfig.setXml(null);
        templateConfig.setController(null);
        // templateConfig.setService(null);
        // templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);  // 大写命名
        strategy.setEntityLombokModel(true); // lombok模型
        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 表名映射策略
        strategy.setNaming(NamingStrategy.underline_to_camel);  // 字段映射策略
        strategy.setTablePrefix("sys_", "tb_", "school_", "exam_", "net_", "pendi_");  // 表前缀
        strategy.setInclude(tableNames);
        mpg.setStrategy(strategy);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        // 模板引擎是 beetl
        String templatePath = "/templates/mapper.xml.btl";

        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                StringBuilder outPutPath = new StringBuilder()
                        .append(projectPath)
                        .append("/src/main/resources/mapper");
                try {
                    String mapperModuleName = CodeGenerator.packageName.split("\\.")[3];
                    outPutPath.append("/").append(mapperModuleName);
                } catch (Exception ex) {
                    log.debug("不是第三个");
                }

                return String.format("%s/%sMapper%s",
                        outPutPath, tableInfo.getEntityName(), StringPool.DOT_XML);
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模板引擎
        mpg.setTemplateEngine(new BeetlTemplateEngine());

        mpg.execute();
    }
}

