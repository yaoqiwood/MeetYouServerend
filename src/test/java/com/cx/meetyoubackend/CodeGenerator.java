package com.cx.meetyoubackend;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.env.Environment;

public class CodeGenerator {

  private final DataSourceProperties dataSourceProperties;
  private final Environment environment;

  public CodeGenerator(DataSourceProperties dataSourceProperties, Environment environment) {
    this.dataSourceProperties = dataSourceProperties;
    this.environment = environment;
  }

  public void generate() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入模块名：");
    String moduleName = scanner.nextLine();
    System.out.println("请输入表名：");
    String tableName = scanner.nextLine();

    String basicPath = System.getProperty("user.dir") + "/src/main/java";

    FastAutoGenerator.create(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(),
            dataSourceProperties.getPassword())
        .globalConfig(builder -> {
          builder.author("your-name")
              .enableSwagger()
              .fileOverride()
              .outputDir(basicPath);
        })
        .packageConfig(builder -> {
          builder.parent("com.cx")
              .moduleName(moduleName)
              .entity("entity") // 设置实体类包名
              .service("service") // 设置Service接口包名
              .serviceImpl("service.impl") // 设置Service实现类包名
              .mapper("dao") // 设置Mapper接口包名
              .xml("dao.xml")
              .controller("controller"); // 设置Controller包名
        })
        .strategyConfig(builder -> {
          builder.addInclude(tableName)
              .addTablePrefix("t_", "c_")
              .entityBuilder()
              .enableLombok()
              .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
              .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
              .formatFileName("%sEntity")
              .enableTableFieldAnnotation()
              .build();
        })
        .execute();
  }
}
