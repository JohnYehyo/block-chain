package com.johnyehyo.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnyehyo.blockchain.entity.Block;
import com.johnyehyo.blockchain.entity.Chain;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-25 16:42
 * @Version 1.0
 */
public class BlockChainTest {

    @Test
    public void chainTest() throws JsonProcessingException {
        Chain chain = Chain.initChain();

        String preHash = chain.getLast().getHash();
        Block block = new Block(preHash, "测试张入矫");

        chain.add(block);

        String preHash1 = chain.getLast().getHash();
        Block block1 = new Block(preHash1, "测试李入矫");

        chain.add(block1);
        System.out.println(new ObjectMapper().writeValueAsString(chain));

        Assert.notNull(block, "区块生成不成功");
        Assert.notNull(block1, "区块生成不成功");
        Assert.isTrue(block1.getPreHash().equals(block.getHash()), "前后链不连续");
    }
}
