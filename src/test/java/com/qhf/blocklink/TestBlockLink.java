package com.qhf.blocklink;

import org.junit.jupiter.api.Test;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class TestBlockLink {
    @Test
    public void blockLink(){
        Block block0 = new Block(null, "");
        Block block1 = new Block(null, block0.getHash());
        Block block2 = new Block(null, block1.getHash());

        BlockChain blockChain = new BlockChain();
        blockChain.addBlock(block0);
        blockChain.addBlock(block1);
        blockChain.addBlock(block2);
        System.out.println(blockChain);
    }
}
