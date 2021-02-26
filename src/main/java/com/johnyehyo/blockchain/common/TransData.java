package com.johnyehyo.blockchain.common;

import lombok.Data;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-26 17:59
 * @Version 1.0
 */
@Data
public class TransData {

    /**
     * 签名供他人验证
     */
    private String sign;

    /**
     * 该操作的hash
     */
    private String hash;

    /**
     * 操作人的公钥
     */
    private String publicKey;

    /**
     * 记录
     */
    private String data;
}
