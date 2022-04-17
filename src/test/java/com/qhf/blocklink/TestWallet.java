package com.qhf.blocklink;

import org.junit.jupiter.api.Test;

/**
 * @author qihongfei
 * @Date 2022/4/17
 */
public class TestWallet {
    @Test
    public void wallet(){
        Wallet wallet = new Wallet();
        //OTA0YTA5ZTNiYjFiMmI0OGRiYzBlNDU0OWQzOWNiZDAxM2E4YWNhOWYzNmIwMzI3MDhiZTRjNWMxZjc4MzlkYg==
        System.out.println(wallet.address());
        Transaction transaction = new Transaction("a","b",0.9);
        byte[] qhfs = wallet.sign(transaction.toString());
        boolean verifySign = wallet.verifySign(transaction.toString(), qhfs);
        System.out.println(verifySign);
    }
}
