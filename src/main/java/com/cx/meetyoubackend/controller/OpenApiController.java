package com.cx.meetyoubackend.controller;

import com.cx.meetyoubackend.constans.ResponseBodyEnum;
import com.cx.meetyoubackend.utils.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
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

	/**
	 * @return
	 */
	@GetMapping("/generate-temp-token")
	public ResponseEntity<?> generateTempToken() {
		String tempToken = UUID.randomUUID().toString();
		Map<String, Object> response = new HashMap<>();
		response.put(ResponseBodyEnum.TEMP_TOKEN.getValue(), tempToken);
		return ResponseEntity.ok(response); // 返回临时令牌给前端
	}
}
