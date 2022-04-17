package com.qhf.blocklink;

import org.junit.jupiter.api.Test;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class TestMine {
    @Test
    public void proof(){
        Block b = new Block(null, "");
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
