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
 * @since 2023-11-04
 */
@Getter
@Setter
@TableName("web_user")
@ApiModel(value = "WebUserEntity对象", description = "")
public class WebUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("web_username")
    private String webUsername;

    @TableField("web_password")
    private String webPassword;

    @TableField("web_salt")
    private String webSalt;

    @TableField("user_type")
    private String userType;

    @TableField("web_update_time")
    private LocalDateTime webUpdateTime;

    @TableField("web_create_time")
    private LocalDateTime webCreateTime;

    @TableField("web_remark")
    private String webRemark;
}
