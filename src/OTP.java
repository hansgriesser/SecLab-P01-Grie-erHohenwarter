import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class OTP {

    // Beispielwerte für die Parameter
    private static final byte[] KEY = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    private static final int UID = 42; // Beispiel Benutzerkennung
    private static final int L = 8; // Länge des OTP (nur pseudozufälliger Anteil)
    private static final int V = 30; // Gültigkeitsdauer (in Sekunden)
    private static final long T = System.currentTimeMillis() / 1000; // Aktuelle Zeit in Sekunden

    public static void main(String[] args) throws Exception {
        // OTP mit AES basierter Funktion
        String otpAES = generateOTPUsingAES(KEY, UID, L, V, T);
        System.out.println("OTP (AES): " + otpAES);

        // OTP mit Hash basierter Funktion
        String otpHash = generateOTPUsingHash(KEY, UID, L, V, T);
        System.out.println("OTP (Hash): " + otpHash);
    }

    public static String generateOTPUsingAES(byte[] key, int uid, int l, int v, long t) throws Exception {
        // Kombiniere die Parameter in eine Byte-Puffer
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + 4 + 8);
        buffer.putInt(uid);
        buffer.putInt(l);
        buffer.putInt(v);
        buffer.putLong(t);

        // Padding, falls erforderlich (hier einfach null-Bytes)
        byte[] paddedInput = Arrays.copyOf(buffer.array(), 16);

        // AES Initialisierung
        Cipher aes = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
        aes.init(Cipher.ENCRYPT_MODE, aesKey);

        // Verschlüsselung und Auswahl der rechten L Bytes
        byte[] encrypted = aes.doFinal(paddedInput);
        byte[] otpBytes = Arrays.copyOfRange(encrypted, encrypted.length - l, encrypted.length);

        // OTP zusammenstellen
        String otp = String.format("%d%d%d", uid, l, v) + bytesToHex(otpBytes);
        return otp;
    }

    public static String generateOTPUsingHash(byte[] key, int uid, int l, int v, long t) throws Exception {
        // Kombiniere die Parameter in eine Byte-Puffer
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + 4 + 8);
        buffer.putInt(uid);
        buffer.putInt(l);
        buffer.putInt(v);
        buffer.putLong(t);

        // Verkette den Schlüssel
        ByteBuffer keyBuffer = ByteBuffer.allocate(buffer.capacity() + key.length);
        keyBuffer.put(buffer.array());
        keyBuffer.put(key);

        // Hash Berechnung (z.B. SHA-256)
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(keyBuffer.array());

        // Auswahl der rechten L Bytes
        byte[] otpBytes = Arrays.copyOfRange(hash, hash.length - l, hash.length);

        // OTP zusammenstellen
        String otp = String.format("%d%d%d", uid, l, v) + bytesToHex(otpBytes);
        return otp;
    }

    // Hilfsfunktion zur Umwandlung von Bytes in Hex-String
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
