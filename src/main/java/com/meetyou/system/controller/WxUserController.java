package com.meetyou.system.controller;

import com.meetyou.system.common.result.Result;
import com.meetyou.system.model.entity.WxUser;
import com.meetyou.system.service.WxUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "08.外部接口")
@RestController
@RequestMapping("/api/v1/wxUser")
@RequiredArgsConstructor
public class WxUserController {

	private final WxUserService wxUserService;

	@GetMapping("getWxUsers")
	public Result getWxUsers() {
		List<WxUser> wxUserList = wxUserService.list();
		return Result.success(wxUserList);
	}
}
