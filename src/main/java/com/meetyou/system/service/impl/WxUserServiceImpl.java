package com.meetyou.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meetyou.system.mapper.WxUserMapper;
import com.meetyou.system.model.entity.WxUser;
import com.meetyou.system.service.WxUserService;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

}
