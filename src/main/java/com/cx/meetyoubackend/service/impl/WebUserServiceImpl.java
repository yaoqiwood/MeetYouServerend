package com.cx.meetyoubackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.meetyoubackend.constans.CaptchaConstants;
import com.cx.meetyoubackend.dao.WebUserMapper;
import com.cx.meetyoubackend.entity.WebUserEntity;
import com.cx.meetyoubackend.pojo.WebUserDto;
import com.cx.meetyoubackend.service.IWebUserService;
import com.cx.meetyoubackend.service.RedisCrudService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author your-name
 * @since 2023-11-04
 */
@Service
public class WebUserServiceImpl extends ServiceImpl<WebUserMapper, WebUserEntity> implements
	IWebUserService {


	@Autowired
	private RedisCrudService redisService;

	// 假设有一个方法来验证用户名和密码
	private boolean validateCredentials(String username, String password) {
		// 实现用户验证逻辑
		return true; // 假设用户验证成功
	}

	@Override
	public ResponseEntity<?> login(WebUserDto userDto, String sessionId) {

		// 检查登录失败次数
		Integer failCount = Integer.parseInt(
			redisService.get(CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId).toString());
		if (failCount != null && failCount >= 3) {
			// 需要验证码验证
			String actualCaptcha =
				redisService.get(CaptchaConstants.CAPTCHA_CODE_PREFIX.getValue()) + sessionId;
			if (actualCaptcha == null || !actualCaptcha.equals(userDto.getCaptCha())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Captcha is incorrect.");
			}
		} else {
			failCount = 0;
		}

		// 验证用户名和密码
		if (validateCredentials(userDto.getWebUsername(), userDto.getWebPassword())) {
			// 登录成功，重置登录失败次数
			redisService.delete(CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId);
			// 返回成功响应
			return ResponseEntity.ok("Login successful.");
		} else {
			// 登录失败，增加失败次数
			redisService.setWithExpireTime(
				CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId,
				failCount + 1, 10,
				TimeUnit.MINUTES);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
		}
	}
}
