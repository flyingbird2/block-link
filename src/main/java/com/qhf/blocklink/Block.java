package com.qhf.blocklink;

import com.google.common.hash.Hashing;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * 区块的结构
 *
 * @author qihongfei
 * @Date 2022/4/17
 */
@Data
public class Block {
    String data;
    String prev_hash;
    long timestamp;
    String hash;
    /**
     * 随机数
     */
    String Nonce;

    public Block(String data, String prev_hash) {
        this.prev_hash = prev_hash;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        String readyForEncode = prev_hash + data + timestamp;
        String sha256hex = Hashing.sha256()
                .hashString(readyForEncode
                        , StandardCharsets.UTF_8)
                .toString();
        this.hash = sha256hex;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {





    }
}
