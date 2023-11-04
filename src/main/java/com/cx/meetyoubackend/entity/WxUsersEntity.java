package com.cx.meetyoubackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author your-name
 * @since 2023-10-30
 */
@Getter
@Setter
@TableName("wx_users")
@ApiModel(value = "WxUsersEntity对象", description = "")
public class WxUsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wx_id", type = IdType.AUTO)
    private Integer wxId;

    @TableField("wx_username")
    private String wxUsername;

    @TableField("wx_real_name")
    private String wxRealName;

    @TableField("wx_openId")
    private String wxOpenid;

    @TableField("wx_gender")
    private String wxGender;

    @TableField("wx_phone_number")
    private String wxPhoneNumber;

    @TableField("wx_update_time")
    private LocalDateTime wxUpdateTime;

    @TableField("wx_create_time")
    private LocalDateTime wxCreateTime;

    @TableField("wx_remarks")
    private String wxRemarks;

    @TableField("wx_id_card_number")
    private String wxIdCardNumber;
}
