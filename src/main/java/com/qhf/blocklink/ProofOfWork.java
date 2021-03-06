package com.qhf.blocklink;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * 工作量证明
 *
 * @author qihongfei
 * @Date 2022/4/17
 */
@Data
public class ProofOfWork {
    //定义工作量难度，表示有效的哈西值为 5个0开头的
    int difficulty = 5;
    Block block;
    Wallet user;

    ProofOfWork(Block block, Wallet user) {
        this.block = block;
        this.user = user;
    }

    ProofOfWork(Block block) {
        this.block = block;
    }

    /**
     * 挖矿函数
     */
    void mine() {
        String ready = this.block.getPrev_hash() + this.block.getTransactionList()
                + this.block.getTimestamp();
        int i = 0;
        while (true) {
            String sha256hex = Hashing.sha256()
                    .hashString(ready + i
                            , StandardCharsets.UTF_8)
                    .toString();
            if (sha256hex.startsWith("00000")) {
                System.out.println(sha256hex);
                this.block.setHash(sha256hex);
                this.block.setNonce(i + "");
                //挖矿获得奖励
                Transaction transaction = new Transaction("", user.address(), 1L);
                this.block.getTransactionList().add(transaction);
                break;
            }
            i = i + 1;
        }
    }

    /**
     * 验证有效性
     */
    boolean validate(Block block) {
        String readyForValidate = block.getPrev_hash() + block.getTransactionList()
                + block.getTimestamp() + block.getNonce();
        String sha256hex = Hashing.sha256()
                .hashString(readyForValidate
                        , StandardCharsets.UTF_8)
                .toString();
        if (sha256hex.startsWith("00000")) {
            return true;
        }
        return false;
    }

}
