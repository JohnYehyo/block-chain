package com.johnyehyo.blockchain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnyehyo.blockchain.common.SecurityUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-25 12:36
 * @Version 1.0
 */
@Data
public class Block {

    /**
     * 前块hash
     */
    private String preHash;


    /**
     * 记录数据
     */
    private String data;


    /**
     * 本块hash
     */
    private String hash;

    /**
     * nonce
     */
    private int nonce = 1;

    /**
     * difficulty
     */
    private int difficulty = 5;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * 祖先区块hash
     */
    private static final String ANCESTOR_HASH = "0";


    public Block(String preHash, String data) {
        this.preHash = preHash;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        this.hash = computeHash();
    }

    public Block(String preHash, String data, int difficulty) {
        this.preHash = preHash;
        this.data = data;
        this.difficulty = difficulty;
        this.timestamp = System.currentTimeMillis();
        this.hash = computeHash();
    }

    /**
     * 计算hash
     * @return
     */
    public String computeHash() {
        return SecurityUtils.applySha256(preHash + data + nonce + timestamp);
    }

    /**
     * 工作量证明
     */
    public void mineBlock(){
        //复杂度控制
        String target = new String(new char[difficulty]).replace('\0', '0');
        String comHash;
        while (true) {
            comHash = SecurityUtils.applySha256(preHash + data + nonce + timestamp);
            if (target.equals(comHash.substring(0, difficulty))) {
                break;
            }
            nonce++;
        }
    }

}
