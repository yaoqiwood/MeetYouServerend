package com.cx.meetyoubackend.security;

import com.cx.meetyoubackend.constans.CaptchaConstants;
import com.cx.meetyoubackend.service.RedisCrudService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {


	private final RedisCrudService redisCrudService;

	@Autowired
	public MyInterceptor(RedisCrudService redisCrudService) {
		this.redisCrudService = redisCrudService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler)
		throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();

		// 定义不需要验证token的URL
		List<String> excludeUrls = Arrays.asList("/login", "/register", "/getCaptcha");

		// 如果是不需要验证token的URL，直接放行
		if (excludeUrls.contains(url)) {
			return true;
		}

		// 在这里添加你的逻辑
		// 例如，检查请求头中的令牌
		String token = request.getHeader("X-Token");
		String redisToken = redisCrudService.get(CaptchaConstants.LOGIN_SUCCESS_PREFIX.getValue())
			+ request.getSession().getId();
		if (redisToken.equals(token)) {
			return true;  // 如果验证成功，返回true继续执行
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		return false;  // 如果验证失败，返回false，请求将被拦截
	}
}

