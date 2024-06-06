public class Verification {
public static void verifyOTP(String otp, long sysTime){
    String verifyOTP = otp;

    int uid = Integer.parseInt(verifyOTP.substring(0, 2));
    int otpLength = Integer.parseInt(verifyOTP.substring(2, 4));
    int valPer = Integer.parseInt(verifyOTP.substring(4, 6));

    String[] OTPs = generateOTPs(uid, otpLength, valPer, sysTime);

    if(contains(OTPs, verifyOTP)) System.out.println("Verification succeeded.");
    else System.out.println("Verification failed.");

    }
    private static boolean contains(String[] array, String value){
        for (String s : array) {
            if (s.equals(value)){
                return true;
            }
        }
        return false;
    }

    private static String[] generateOTPs(int uid, int otpLength, int valper, long sysTime){
        long futureTime = sysTime + 15;
        long sysTime1 = sysTime - 15;
        String[] OTPs = new String[31];
        int counter = 0;
        OTP otp = new OTP();
        while(sysTime1 <= futureTime){
            OTPs[counter] = otp.generateOTP(uid, otpLength, valper, sysTime1);
            counter++;
            sysTime1++;
        }
        return OTPs;
    }
}
