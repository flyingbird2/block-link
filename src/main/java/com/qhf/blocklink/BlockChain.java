package com.qhf.blocklink;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
@Data
public class BlockChain {
    List<Block> blocks = new LinkedList<>();


    public void addBlock(Block block){

        blocks.add(block);
    }
}
