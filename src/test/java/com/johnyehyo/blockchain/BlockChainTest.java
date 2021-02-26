package com.johnyehyo.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnyehyo.blockchain.common.ChainCheckUtils;
import com.johnyehyo.blockchain.common.SecurityUtils;
import com.johnyehyo.blockchain.common.TransData;
import com.johnyehyo.blockchain.common.TrustSdk;
import com.johnyehyo.blockchain.common.exception.TrustSDKException;
import com.johnyehyo.blockchain.entity.Block;
import com.johnyehyo.blockchain.entity.Chain;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-25 16:42
 * @Version 1.0
 */
public class BlockChainTest {

    private static final String publicKey = "A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54";
    private static final String privateKey = "yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=";

    @Test
    public void chainTest() throws JsonProcessingException, UnsupportedEncodingException, TrustSDKException {
        Chain chain = Chain.initChain();

        String preHash = chain.getLast().getHash();
        TransData transData = new TransData();
        String data = "测试张入矫表单数据...";
        transData.setData(data);
        transData.setPublicKey(publicKey);
        //设置签名，供其他人验证
        transData.setSign(TrustSdk.signString(privateKey, data));
        //设置hash，防止篡改
        transData.setHash(SecurityUtils.applySha256(data));
        Block block = new Block(preHash, transData.toString());

        Assert.notNull(block, "区块生成不成功");

        //工作量证明
        block.mineBlock();
        chain.add(block);

        String preHash1 = chain.getLast().getHash();
        TransData transData1 = new TransData();
        String data1 = "测试张入矫表单数据...";
        transData1.setData(data);
        transData.setPublicKey(publicKey);
        //设置签名，供其他人验证
        transData1.setSign(TrustSdk.signString(privateKey, data1));
        //设置hash，防止篡改
        transData1.setHash(SecurityUtils.applySha256(data1));
        Block block1 = new Block(preHash1, transData1.toString());

        Assert.notNull(block1, "区块生成不成功");

        //工作量证明
        block1.mineBlock();
        chain.add(block1);

        Assert.isTrue(ChainCheckUtils.isChainValid(chain), "前后链断裂!");

        System.out.println(new ObjectMapper().writeValueAsString(chain));

    }
}
