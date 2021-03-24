package com.weida.epcommon.jwt;

import lombok.Data;

import java.util.Date;

/**
 * 载荷实体
 *
 * @param <T>
 * @author apelx
 * @since 2020-11-22
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}