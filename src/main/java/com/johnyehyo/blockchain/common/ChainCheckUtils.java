package com.johnyehyo.blockchain.common;

import com.johnyehyo.blockchain.entity.Block;
import com.johnyehyo.blockchain.entity.Chain;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-25 16:55
 * @Version 1.0
 */
public class ChainCheckUtils {

//    public static Boolean isChainValid(Chain chain) {
//        Block currentBlock;
//        Block previousBlock;
//
//        for (int i = 1; i < chain.size(); i++) {
//            currentBlock = chain.get(i);
//            previousBlock = chain.get(i - 1);
//            if (!currentBlock.getHash().equals(currentBlock.computeHash(currentBlock.getPreHash(),
//                    currentBlock.getData()))) {
//                System.out.println("区块hash值不匹配");
//                return false;
//            }
//            if (!previousBlock.getHash().equals(currentBlock.getPreHash())) {
//                System.out.println("链结构不匹配");
//                return false;
//            }
//        }
//        return true;
//    }
}
