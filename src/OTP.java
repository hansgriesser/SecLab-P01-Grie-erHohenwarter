import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
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
    public String generateOTP(int uid, int OTPLength, int valPer){
        long sysTime = getSysTime();

        //Create random generated key
//        byte[] key = new byte[16];
//        SecureRandom random = new SecureRandom();
//        random.nextBytes(key);
        byte[] key = new byte[] {
                (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
                (byte) 0x10, (byte) 0x32, (byte) 0x54, (byte) 0x76,
                (byte) 0x98, (byte) 0xBA, (byte) 0xDC, (byte) 0xFE
        };


        String dataToEncrypt = String.valueOf(uid + OTPLength + valPer + sysTime);
        byte[] dataToEncryptBytes = padData(dataToEncrypt.getBytes(StandardCharsets.UTF_8), 16);

        //Generate AES with random generated kes from above
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
        aes.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = aes.doFinal(dataToEncryptBytes);

        String fk = Base64.getEncoder().encodeToString(encrypted).substring(0, OTPLength);
        String otp = "" + uid + "" + OTPLength + "" + valPer + "" + fk;
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

    // Methode zum Padding der Daten
    public static byte[] padData(byte[] data, int blockSize) {
        int paddingLength = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + paddingLength];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        return paddedData;
    }
}
