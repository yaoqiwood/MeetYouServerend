package com.cx.meetyoubackend.controller;

import com.cx.meetyoubackend.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/open/")
public class OpenApiController {

  @GetMapping("test")
  public void test() {
    LogUtil.info("Success!");
  }
}
