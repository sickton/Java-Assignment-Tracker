package model.Security;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Authenticator {

    /** The key size required for RSA public and private key*/
    private final int KEY_SIZE = 2048;

    /** Method to generate the public and private keys to use for all users.
     * public key - helps users to store their passwords (can be shared)
     * private key - helps decrypt the passwords and is restricted to the platform
     * @throws IllegalArgumentException if the file does not exist or the algorithm can't be used
     */
    public void generateKeyPairs() {
        try
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(KEY_SIZE);
            KeyPair keys = generator.generateKeyPair();
            byte[] publicKey = Base64.getEncoder().encode(keys.getPublic().getEncoded());
            byte[] privateKey = Base64.getEncoder().encode(keys.getPrivate().getEncoded());
            PrintWriter pw;
            try (FileOutputStream outputPublic = new FileOutputStream("public.key")) {
                pw = new PrintWriter(outputPublic);
                pw.println(new String(publicKey));
                pw.close();
            }
            try (FileOutputStream outputPrivate = new FileOutputStream("private.key")) {
                pw = new PrintWriter(outputPrivate);
                pw.println(new String(privateKey));
                pw.close();
            }
        }
        catch(IOException | NoSuchAlgorithmException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Main method to create both the keys
     * @param args any command line arguments
     */
    public static void main(String args[])
    {
        Authenticator a = new Authenticator();
        a.generateKeyPairs();
    }
}