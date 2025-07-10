package model.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class RSAUtility {
    /**
     * Method to return the public key used in encrypting password of new users with RSA
     * @return public key for RSA encryption
     * @throws IllegalArgumentException if the public key does not exist
     */
    public static PublicKey getPublicKey() {
        try {
            FileInputStream fs = new FileInputStream("public.key");
            Scanner sc = new Scanner(fs);
            String key = sc.nextLine();
            byte[] keyBytes = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keys = KeyFactory.getInstance("RSA");
            sc.close();
            fs.close();
            return keys.generatePublic(spec);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException("Could not generate the public key");
        }
    }

    /**
     * Method to return the private key used for decrypting a password using RSA
     * @return private key for RSA decryption
     * @throws IllegalArgumentException if the private key does not exist
     */
    public static PrivateKey getPrivateKey() {
        try {
            FileInputStream fs = new FileInputStream("private.key");
            Scanner sc = new Scanner(fs);
            String key = sc.nextLine();
            byte[] keyBytes = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keys = KeyFactory.getInstance("RSA");
            sc.close();
            fs.close();
            return keys.generatePrivate(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException("Could not generate the private key");
        }
    }

    /**
     * Method to encrypt a given text with the public key
     * @param text text to be encrypted
     * @return encrypted text
     */
    public static String encrypt(String text)
    {
        Cipher encoder = null;
        try {
            encoder = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            encoder.init(Cipher.ENCRYPT_MODE, getPublicKey());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] pass = null;
        try {
            pass = encoder.doFinal(text.getBytes());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new String(Base64.getEncoder().encode(pass));
    }

    /**
     * Method to decrypt an encrypted text
     * @param encrypted encrypted text
     * @return decrypted string
     */
    public static String decrypt(String encrypted)
    {
        try {
            Cipher decoder = Cipher.getInstance("RSA");
            decoder.init(Cipher.DECRYPT_MODE, getPrivateKey());
            byte[] decryptedBytes = Base64.getDecoder().decode(encrypted);
            byte[] pass = decoder.doFinal(decryptedBytes);
            return new String(pass);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            throw new IllegalArgumentException("Could not retrive Password!");
        }
    }
}
