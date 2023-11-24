package com.cx.meetyoubackend.controller;

import com.cx.meetyoubackend.constans.CaptchaConstants;
import com.cx.meetyoubackend.service.RedisCrudService;
import com.cx.meetyoubackend.utils.CaptchaUtil;
import com.cx.meetyoubackend.utils.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {

	@Autowired
	private RedisCrudService redisCrudService;

	@GetMapping(value = "/getCaptcha", produces = MediaType.IMAGE_JPEG_VALUE)
	public void captcha(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		// 获取用户的tempTokenKey
		String tempTokenKey = request.getHeader(CaptchaConstants.TEMP_TOKEN_KEY.getValue());
		// 生成验证码字符串
		String captchaCode = CaptchaUtil.generateCaptchaCode();
		// 5分钟
		redisCrudService.setWithExpireTime(
			CaptchaConstants.CAPTCHA_CODE_PREFIX.getValue() + tempTokenKey,
			captchaCode, 5, TimeUnit.MINUTES);
		System.out.println(
			CaptchaConstants.CAPTCHA_CODE_PREFIX.getValue() + tempTokenKey);
		LogUtil.info(String.valueOf(redisCrudService.get(
			(CaptchaConstants.CAPTCHA_CODE_PREFIX.getValue()) + tempTokenKey)));
		// 生成验证码图像
		BufferedImage image = CaptchaUtil.generateCaptchaImage(captchaCode);

		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 将图像输出到Servlet输出流中
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}
}
