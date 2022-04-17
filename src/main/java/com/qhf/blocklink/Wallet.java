package com.qhf.blocklink;


import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.Data;
import org.apache.logging.log4j.util.Base64Util;
import sun.security.tools.KeyStoreUtil;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * 钱包
 *
 * @author qihongfei
 * @Date 2022/4/17
 */
@Data
public class Wallet {
    PrivateKey privateKey;
    PublicKey publicKey;

    Wallet() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // curveName这里取值：secp256k1
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        try {
            keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        this.privateKey = privateKey;
        this.publicKey = publicKey;

    }

    /**
     * 通过公钥生成地址
     *
     * @return
     */
    public String address() {
        HashCode hashCode = Hashing.sha256()
                .hashBytes(publicKey.getEncoded());
        String encode = Base64Util.encode(hashCode.toString());
        return encode;
    }

    public byte[] sign(String message) {
        //HashCode hashCode = Hashing.sha256()
        //.hashBytes(message.getBytes());
        byte[] sha256withECDSAS = signDataUtil("SHA256withECDSA", message.getBytes(), privateKey);

        return sha256withECDSAS;
    }

    public boolean verifySign(String message, byte[] sign) {
        boolean sha256withECDSA = verifySignUtil("SHA256withECDSA", message.getBytes(), publicKey, sign);
        return sha256withECDSA;

    }

    public byte[] signDataUtil(String algorithm, byte[] data, PrivateKey key) {

        try {
            Signature signer = Signature.getInstance(algorithm);
            signer.initSign(key);
            signer.update(data);
            return (signer.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean verifySignUtil(String algorithm, byte[] data, PublicKey key, byte[] sig) {
        Signature signer = null;
        try {
            signer = Signature.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            signer.initVerify(key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            signer.update(data);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        try {
            return (signer.verify(sig));
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }


}
