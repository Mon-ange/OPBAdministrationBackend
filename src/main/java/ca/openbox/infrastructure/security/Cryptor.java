package ca.openbox.infrastructure.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

@Component
public class Cryptor {
    @Value("${secret.key}")
    String keyString;

    @Value("${secret.iv}")
    String ivString;
    private static byte[] getUTF8Bytes(final String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }


    final String transform = "AES/CBC/PKCS5Padding";
    final Properties properties = new Properties();
    final String algorithm = "AES/CBC/PKCS5Padding";
    public SecretKeySpec getSecretKeySpec(){
        final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes(keyString), "AES");
        return key;
    }
    public IvParameterSpec getIvParameterSpec(){
        final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes(ivString));
        return iv;
    }
    public String encrypt(String input)throws Exception{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIvParameterSpec());
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
    }

    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(), getIvParameterSpec());
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    /*public static void main(String argv[]) throws Exception {
        Cryptor cryptor = new Cryptor();
        String decrypt = cryptor.decrypt("pd6wD/j/+ghvMgv/E1p8Fg==");
        System.out.println(decrypt);
    }*/
}
