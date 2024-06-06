public class main {
    public static void main(String[] args) throws Exception {
        byte[] KEY = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
        int UID = 42; // Beispiel Benutzerkennung
        int L = 8; // Länge des OTP (nur pseudozufälliger Anteil)
        int V = 30; // Gültigkeitsdauer (in Sekunden)
        long T = System.currentTimeMillis() / 1000;

        System.out.println(OTPGenerator.generateOTPUsingHash(KEY,UID,L,V,T));
    }
}
