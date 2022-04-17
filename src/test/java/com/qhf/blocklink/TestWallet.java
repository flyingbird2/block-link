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
        byte[] qhfs = wallet.sign("QHF");
        boolean verifySign = wallet.verifySign("QHF", qhfs);
        System.out.println(verifySign);
    }
}
