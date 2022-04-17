package com.qhf.blocklink;

import org.apache.logging.log4j.util.Base64Util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * https://www.orchome.com/1050
 * getEncoded() 输出进行Base64编码（64个字节添加换行符），然后首尾添加响应的分割字符串
 * @author qihongfei
 * @Date 2022/4/17
 */
public class PEMUtil {
    public static void savePublicKeyAsPEM(PublicKey publicKey, String name) throws Exception {
        String content = Base64Util.encode(String.valueOf(publicKey.getEncoded()));
        File file = new File(name);
        if ( file.isFile() && file.exists() ) {
            throw new IOException("file already exists");
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.write("-----BEGIN PUBLIC KEY-----\n".getBytes());
            int i = 0;
            for (; i<(content.length() - (content.length() % 64)); i+=64) {
                randomAccessFile.write(content.substring(i, i + 64).getBytes());
                randomAccessFile.write('\n');
            }

            randomAccessFile.write(content.substring(i, content.length()).getBytes());
            randomAccessFile.write('\n');

            randomAccessFile.write("-----END PUBLIC KEY-----".getBytes());
        }
    }

    public static void main(String[] args) throws Exception {
        Wallet wallet = new Wallet();
        savePublicKeyAsPEM(wallet.getPublicKey(), "publickey.der");

    }

    public static void savePrivateKeyAsPEM(PrivateKey privateKey, String name) throws Exception {
        String content = Base64Util.encode(String.valueOf(privateKey.getEncoded()));
        File file = new File(name);
        if ( file.isFile() && file.exists() ) {
            throw new IOException("file already exists");
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.write("-----BEGIN PRIVATE KEY-----\n".getBytes());
            int i = 0;
            for (; i<(content.length() - (content.length() % 64)); i+=64) {
                randomAccessFile.write(content.substring(i, i + 64).getBytes());
                randomAccessFile.write('\n');
            }

            randomAccessFile.write(content.substring(i, content.length()).getBytes());
            randomAccessFile.write('\n');

            randomAccessFile.write("-----END PRIVATE KEY-----".getBytes());
        }
    }

}
