package com.qhf.blocklink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;

/**
 * 交易
 *
 * @author qihongfei
 * @Date 2022/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    /**
     * 发送方
     */
    private String sender;
    /**
     * 接收方
     */
    private String receiver;
    /**
     * 交易数量
     */
    private double amount;

    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getUnionKey() {
        return this.sender + this.receiver + this.amount;
    }

    private PublicKey sendPublicKey;

    private byte[] sendSign;

    /**
     * 为了验证交易可靠性，需要发送方输入他的公钥和签名
     *
     * @param sign
     * @param publicKey
     */
    void setSign(byte[] sign, PublicKey publicKey) {
        this.sendSign = sign;
        this.sendPublicKey = publicKey;
    }


}
