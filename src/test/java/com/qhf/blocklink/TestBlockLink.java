package com.qhf.blocklink;

import org.junit.jupiter.api.Test;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class TestBlockLink {
    @Test
    public void blockLink(){
        Block block0 = new Block("创世区块", "");
        Block block1 = new Block("张三转给李四1个比特币", block0.getHash());
        Block block2 = new Block("张三转给王五2个比特币", block1.getHash());

        BlockChain blockChain = new BlockChain();
        blockChain.addBlock(block0);
        blockChain.addBlock(block1);
        blockChain.addBlock(block2);
        System.out.println(blockChain);
    }
}
