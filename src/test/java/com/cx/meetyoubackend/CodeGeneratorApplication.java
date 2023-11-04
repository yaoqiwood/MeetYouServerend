package com.cx.meetyoubackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CodeGeneratorApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(CodeGeneratorApplication.class,
        args);
    CodeGenerator generator = context.getBean(CodeGenerator.class);
    generator.generate();
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public CodeGenerator codeGenerator(DataSourceProperties dataSourceProperties,
      Environment environment) {
    return new CodeGenerator(dataSourceProperties, environment);
  }
}
