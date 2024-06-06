import lombok.Getter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OTP {

    private Cipher aes;
//    private byte[] key = new byte[4];
    private byte[] encrypted;


    public String run() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte uid = (byte) 22;
        byte OTP = (byte) 16;
        byte validPer = (byte) 30;

        generateAES(uid, OTP, validPer, getSystemTime());

        encrypt();
        return "";
    }

    private byte getSystemTime(){
        long now = System.currentTimeMillis() / 100;
        return (byte) now;
    }

    private byte[] generateAES(byte uid, byte OTPLength, byte validPer, byte timeNow) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = new byte[5];


        key[0] = uid; // UID einfügen
        key[1] = OTPLength; // OTP länge einfügen
        key[2] = validPer; // Gültigkeitsdauer einfügen
        key[3] = timeNow;


        this.aes = Cipher.getInstance("");

        SecretKeySpec aesKey = new SecretKeySpec(key, "");

        this.aes.init(Cipher.ENCRYPT_MODE, aesKey);
        return key;
    }

    private void encrypt() throws IllegalBlockSizeException, BadPaddingException {
        this.encrypted = aes.doFinal();

    }
}
