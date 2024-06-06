import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OTP {

    private long now;
    private Cipher aes;

    public void run() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        getSystemTime();
        generateAES();
        encrypt();
    }

    private void getSystemTime(){
        this.now = System.currentTimeMillis() / 100;
    }

    private void generateAES() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = {};

        this.aes = Cipher.getInstance("");

        SecretKeySpec aesKey = new SecretKeySpec(key, "");

        this.aes.init(Cipher.ENCRYPT_MODE, aesKey);

    }

    private void encrypt(){
        byte[] encrypted = aes.doFinal(otp_data);
    }
}
