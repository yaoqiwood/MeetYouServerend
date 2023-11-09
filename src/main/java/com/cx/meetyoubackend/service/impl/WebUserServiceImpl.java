package com.cx.meetyoubackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.meetyoubackend.constans.CaptchaConstants;
import com.cx.meetyoubackend.constans.ResponseBodyEnum;
import com.cx.meetyoubackend.constans.StatusEnum;
import com.cx.meetyoubackend.dao.WebUserMapper;
import com.cx.meetyoubackend.entity.WebUserEntity;
import com.cx.meetyoubackend.pojo.WebUserDto;
import com.cx.meetyoubackend.service.IWebUserService;
import com.cx.meetyoubackend.service.RedisCrudService;
import com.cx.meetyoubackend.utils.BcryptUtil;
import com.cx.meetyoubackend.utils.JwtTokenUtil;
import com.cx.meetyoubackend.utils.NullSafe;
import java.util.HashMap;
import java.util.Map;
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

	private final JwtTokenUtil jwtTokenUtilService;

	@Autowired
	public WebUserServiceImpl(JwtTokenUtil jwtTokenUtilService) {
		this.jwtTokenUtilService = jwtTokenUtilService;
	}


	// 假设有一个方法来验证用户名和密码
	private boolean validateCredentials(String rawPassword, WebUserEntity userEntity) {
		return BcryptUtil.matches(rawPassword, userEntity.getWebPassword()); // 假设用户验证成功
	}

	@Override
	public ResponseEntity<?> login(WebUserDto userDto, String sessionId) {
		// 返回信息
		Map<String, Object> response = new HashMap<>();
		// 检查登录失败次数
		Integer failCount = NullSafe.parseIntX(
			redisService.get(
				NullSafe.toStringX(CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId)));
		if (failCount != null && failCount >= 3) {
			// 需要验证码验证
			String actualCaptcha =
				redisService.get(CaptchaConstants.CAPTCHA_CODE_PREFIX.getValue()) + sessionId;
			if (actualCaptcha == null || !actualCaptcha.equals(userDto.getCaptCha())) {
				response.put(ResponseBodyEnum.MESSAGE.getValue(), "Captcha is incorrect.");
				response.put(ResponseBodyEnum.STATUS.getValue(), StatusEnum.WARNING.getValue());
				response.put(ResponseBodyEnum.SUCCESS.getValue(), false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Captcha is incorrect.");
			}
		} else {
			failCount = 0;
		}

		//	验证是否存在这个账户
		QueryWrapper<WebUserEntity> userEntityQueryWrapper = new QueryWrapper<>();
		userEntityQueryWrapper.lambda().eq(WebUserEntity::getWebUsername, userDto.getWebUsername());
		WebUserEntity userEntity = this.getOne(userEntityQueryWrapper);
		if (userEntity == null) {
			response.put(ResponseBodyEnum.MESSAGE.getValue(), "Non-existent Account");
			response.put(ResponseBodyEnum.STATUS.getValue(), StatusEnum.ERROR.getValue());
			response.put(ResponseBodyEnum.SUCCESS.getValue(), false);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		// 验证用户名和密码
		if (validateCredentials(userDto.getWebPassword(), userEntity)) {
			// 登录成功，重置登录失败次数
			redisService.delete(CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId);
			// 增加redis存储生成的token
			String token = this.jwtTokenUtilService.generateToken(userDto.getWebUsername());
			redisService.setWithExpireTime(
				CaptchaConstants.LOGIN_SUCCESS_PREFIX.getValue() + sessionId, token,
				24, TimeUnit.HOURS);
			// 返回成功响应
			response.put(ResponseBodyEnum.TOKEN.getValue(), token);
			response.put(ResponseBodyEnum.STATUS.getValue(), StatusEnum.SUCCESS.getValue());
			response.put(ResponseBodyEnum.SUCCESS.getValue(), true);
			response.put(ResponseBodyEnum.MESSAGE.getValue(), "Login successful.");
			return ResponseEntity.ok(response);
		} else {
			// 登录失败，增加失败次数
			redisService.setWithExpireTime(
				CaptchaConstants.LOGIN_FAIL_PREFIX.getValue() + sessionId,
				failCount + 1, 10,
				TimeUnit.MINUTES);
			response.put(ResponseBodyEnum.MESSAGE.getValue(), "Login failed.");
			response.put(ResponseBodyEnum.SUCCESS.getValue(), false);
			response.put(ResponseBodyEnum.STATUS.getValue(), StatusEnum.ERROR.getValue());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
}
