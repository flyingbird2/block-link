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


    public void addBlock(Block block) {

        blocks.add(block);
    }

    /**
     * 获取余额
     *
     * @param user
     * @return
     */
    double getBalance(Wallet user) {
        double res = 0L;
        for (Block block : blocks) {
            List<Transaction> transactionList = block.getTransactionList();
            for (Transaction t : transactionList) {
                if (t.getSender().equals(user.address())) {
                    res = res - t.getAmount();
                }
                if (t.getReceiver().equals(user.address())) {
                    res = res + t.getAmount();
                }
            }

        }
        return res;
    }
}
