/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.utilities;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class EncryptDecryptSecret {

    private static final String ALGORITHM = "AES";

    private static final String KEY = "0123456789abcdef";

    /**
     * method to encrypt the password we use the AES encryption algorithm
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String encrypt(String value) throws Exception {
        // generate the key
        Key key = generateKey();
        // create the cipher
        Cipher cipher = Cipher.getInstance(EncryptDecryptSecret.ALGORITHM);
        // Encrypt cipher based on the key
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // generate the byte value from the cipher
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        Encoder encoder = Base64.getEncoder();
        // encode the  using Base64
        String encryptedValue64 = encoder.encodeToString(encryptedByteValue);
        return encryptedValue64;
    }

    /**
     * method to decrypt the string using the AES decryption algorithm
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String decrypt(String value) throws Exception {
        // generate the key
        Key key = generateKey();
        // Encrypt cipher the cipher based on AES algorithm
        Cipher cipher = Cipher.getInstance(EncryptDecryptSecret.ALGORITHM);
        // Decrypt cipher
        cipher.init(Cipher.DECRYPT_MODE, key);
        // get decoder
        Decoder decoder = Base64.getDecoder();
        byte[] decryptedValue64 = decoder.decode(value);
        // Decrypt the the value using the cipher
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
        // return the original string
        String decryptedValue = new String(decryptedByteValue, "utf-8");
        return decryptedValue;

    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(EncryptDecryptSecret.KEY.getBytes(), EncryptDecryptSecret.ALGORITHM);
        return key;
    }

    public static void main(String args[]) {
        try {
            System.out.println(EncryptDecryptSecret.encrypt("reports"));
            System.out.println(EncryptDecryptSecret.decrypt("71txc/cBEP5KoSGvsK1t5A=="));
        } catch (Exception ex) {

        }
    }
}
