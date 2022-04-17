package com.qhf.blocklink;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class TestTransaction {
    @Test
    public void transaction() {
        BlockChain blockChain = new BlockChain();
        Wallet alice = new Wallet();
        Wallet tom = new Wallet();
        Wallet bob = new Wallet();

        System.out.println("alice balance :" + blockChain.getBalance(alice));
        System.out.println("tom balance :" + blockChain.getBalance(tom));
        System.out.println("bob balance :" + blockChain.getBalance(bob));
        //alice 生成创世区块，获得1个加密数字货币
        Block block0 = new Block(Lists.newArrayList(), "");
        ProofOfWork proof = new ProofOfWork(block0, alice);
        proof.mine();
        blockChain.addBlock(block0);
        System.out.println("alice balance :" + blockChain.getBalance(alice));
        //alice向tom转了0.3个加密数字货币


    }
}
