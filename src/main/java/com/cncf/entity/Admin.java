package com.cncf.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 3021596778391682388L;
    private Integer adminId;
    private String adminName;
    private String password;
    private String accessToken;
    private Date registTime;
}