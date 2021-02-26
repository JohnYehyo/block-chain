package com.johnyehyo.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnyehyo.blockchain.common.ChainCheckUtils;
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
        Block block = new Block(preHash, "测试张入矫表单数据...");

        Assert.notNull(block, "区块生成不成功");

        //工作量证明
        block.mineBlock();
        chain.add(block);

        String preHash1 = chain.getLast().getHash();
        Block block1 = new Block(preHash1, "测试李入矫表单数据...");

        Assert.notNull(block1, "区块生成不成功");

        //工作量证明
        block1.mineBlock();
        chain.add(block1);

        Assert.isTrue(ChainCheckUtils.isChainValid(chain), "前后链断裂!");

        System.out.println(new ObjectMapper().writeValueAsString(chain));

    }
}
