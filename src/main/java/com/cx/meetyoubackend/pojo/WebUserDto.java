package com.cx.meetyoubackend.pojo;

import com.cx.meetyoubackend.entity.WebUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WebUserDto extends WebUserEntity {

  private String captCha;
}
