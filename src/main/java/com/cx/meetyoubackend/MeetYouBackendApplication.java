package com.cx.meetyoubackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cx.meetyoubackend.dao")
public class MeetYouBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(MeetYouBackendApplication.class, args);
  }

}
