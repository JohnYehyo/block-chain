package com.johnyehyo.blockchain.entity;

import com.johnyehyo.blockchain.common.ChainCheckUtils;

import java.util.LinkedList;

/**
 * @Description
 * @Author JohnYehyo
 * @Date 2021-02-25 12:36
 * @Version 1.0
 */
public class Chain extends LinkedList<Block>{

    public static Chain initChain() {
        Block initializationBlock = initialization();
        Chain list = new Chain();
        list.add(initializationBlock);
        return list;
    }

    private static Block initialization(){
        return new Block("0", "祖先块");
    }

    private void addBlock(Chain chain, Block block){
        block.mineBlock();
        chain.add(block);
    }

}
