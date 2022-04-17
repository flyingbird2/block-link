package com.qhf.blocklink;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class BlockStarter {
    public static void main(String[] args) {
        Block block0 = new Block("创世区块", "");
        Block block1 = new Block("张三转给李四1个比特币", block0.getHash());
        Block block2 = new Block("张三转给王五2个比特币", block1.getHash());

        BlockChain blockChain = new BlockChain();
        blockChain.addBlock(block0);
        blockChain.addBlock(block1);
        blockChain.addBlock(block2);
        System.out.println(blockChain);


        Block b = new Block("测试", "");
        ProofOfWork proof = new ProofOfWork(b);
        long start = System.currentTimeMillis();
        //0000021f317a09584195e38aac64ca1bd968fd29f3e000121112245926de8dd8
        proof.mine();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        boolean validate = proof.validate(b);
        System.out.println(validate);

    }

}
