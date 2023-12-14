package com.meetyou.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetyou.system.model.bo.UserBO;
import com.meetyou.system.model.bo.UserFormBO;
import com.meetyou.system.model.dto.UserAuthInfo;
import com.meetyou.system.model.entity.SysUser;
import com.meetyou.system.model.query.UserPageQuery;
import com.meetyou.system.model.vo.UserExportVO;
import com.meetyou.system.base.mybatisplus.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户持久层
 *
 * @author haoxr
 * @since 2022/1/14
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "u")
    Page<UserBO> getUserPage(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserFormBO getUserDetail(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    @DataPermission(deptAlias = "u")
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);
}
