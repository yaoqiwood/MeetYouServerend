package com.cx.meetyoubackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.meetyoubackend.entity.WebUserEntity;
import com.cx.meetyoubackend.pojo.WebUserDto;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author your-name
 * @since 2023-11-04
 */
public interface IWebUserService extends IService<WebUserEntity> {

	ResponseEntity<?> login(WebUserDto userDto, String sessionId);
}
