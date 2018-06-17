package com.starServer.entity.vo;

import lombok.Data;


/**
 * Created by xmc1993 on 2017/4/20.
 */
@Data
public class LoginResponseVo {
    private Integer id;
    private String headImgUrl;//头像
    private String nickname;//用户名
    private String accessToken;
    private Boolean IsNewUser = false;
}
