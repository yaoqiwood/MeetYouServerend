package com.cx.meetyoubackend.controller;

import com.cx.meetyoubackend.pojo.WebUserDto;
import com.cx.meetyoubackend.service.IWebUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author your-name
 * @since 2023-11-04
 */
@Controller
public class WebUserController {

	private final IWebUserService userService;

	public WebUserController(IWebUserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(WebUserDto userDto, HttpServletRequest request) {
		return this.userService.login(userDto, request.getSession().getId());
	}


}
