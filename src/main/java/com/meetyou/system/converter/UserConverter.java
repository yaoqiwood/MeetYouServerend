package com.meetyou.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetyou.system.model.bo.UserBO;
import com.meetyou.system.model.bo.UserFormBO;
import com.meetyou.system.model.entity.SysUser;
import com.meetyou.system.model.form.UserForm;
import com.meetyou.system.model.vo.UserImportVO;
import com.meetyou.system.model.vo.UserInfoVO;
import com.meetyou.system.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

	@Mappings({
		@Mapping(target = "genderLabel", expression = "java(com.meetyou.system.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.meetyou.system.common.enums.GenderEnum.class))")
	})
	UserPageVO toPageVo(UserBO bo);

	Page<UserPageVO> toPageVo(Page<UserBO> bo);

	UserForm bo2Form(UserFormBO bo);

	UserForm entity2Form(SysUser entity);

	@InheritInverseConfiguration(name = "entity2Form")
	SysUser form2Entity(UserForm entity);

	@Mappings({
		@Mapping(target = "userId", source = "id")
	})
	UserInfoVO toUserInfoVo(SysUser entity);

	SysUser importVo2Entity(UserImportVO vo);

}
