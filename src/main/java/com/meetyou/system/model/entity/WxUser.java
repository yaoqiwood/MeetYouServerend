package com.meetyou.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import lombok.Data;

@Data
@TableName("wx_users")
public class WxUser {

	@TableId(value = "wx_id", type = IdType.AUTO)
	private Integer wxId;

	@TableField("wx_username")
	private String wxUsername;

	@TableField("wx_real_name")
	private String wxRealName;

	@TableField("wx_openId")
	private String wxOpenId;

	@TableField("wx_gender")
	private String wxGender; // Enum 类型在 Java 中通常用 String 表示

	@TableField("wx_phone_number")
	private String wxPhoneNumber;

	@TableField("wx_update_time")
	private Timestamp wxUpdateTime;

	@TableField("wx_create_time")
	private Timestamp wxCreateTime;

	@TableField("wx_remarks")
	private String wxRemarks;

	@TableField("wx_id_card_number")
	private String wxIdCardNumber;
}

