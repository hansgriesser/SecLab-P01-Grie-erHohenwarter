import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

public class OTP {

    /**
     * Returns system time when the OTP is being generated.
     * @return long
     */
    private long getSysTime(){
        return System.currentTimeMillis() / 1000;
    }

    /**
     * Generates one time password. Takes the length of the OTP as parameter.
     * @param OTPLength integer
     * @return The password as String.
     */
    @SneakyThrows
    public String generateOTP(int OTPLength){
        //Create random generated key
        byte[] key = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);

        //Generate AES with random generated kes from above
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
        aes.init(Cipher.ENCRYPT_MODE, aesKey);

        //Calculation of OTP
        byte[] otpData = ByteBuffer.allocate(Long.BYTES).putLong(getSysTime()).array();
        byte[] encrypted = aes.doFinal(otpData);

        //Print out OTP
//        String otphex = bytesToHex(encrypted).substring(0, OTPLength);
//        String otpBase64 = Base64.getEncoder().encodeToString(encrypted).substring(0, OTPLength);
        String otp = Base64.getEncoder().encodeToString(encrypted).substring(0, OTPLength);
        return otp;

    }

    /**
     * Helper method needed to convert bytes to hex. Will be deleted till the end if not needed.
     * @param bytes bytes
     * @return String
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}

