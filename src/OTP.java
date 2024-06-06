import lombok.Getter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OTP {

    private long now;
    private Cipher aes;
    private byte[] encrypted;
    private byte[] key = {};

    public String run() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        getSystemTime();
        generateAES();
        encrypt();
        return "";
    }

    private void getSystemTime(){
        this.now = System.currentTimeMillis() / 100;
    }

    private void generateAES() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        this.aes = Cipher.getInstance("");

        SecretKeySpec aesKey = new SecretKeySpec(this.key, "");

        this.aes.init(Cipher.ENCRYPT_MODE, aesKey);

    }

    private void encrypt() throws IllegalBlockSizeException, BadPaddingException {
        this.encrypted = aes.doFinal();

    }
}
