public class Verification {
public static void verifyOTP(String otp, long sysTime){
    String verifyOTP = otp;

    String uid = verifyOTP.substring(0, 2);
    String otpLength = verifyOTP.substring(2, 4);
    String valPer = verifyOTP.substring(4, 6);

    OTP otpgenerate = new OTP();
    String newOTP = otpgenerate.generateOTP(Integer.parseInt(uid), Integer.parseInt(otpLength), Integer.parseInt(valPer), sysTime);
    System.out.println("Old: " + verifyOTP + " = New: " + newOTP);
    if(verifyOTP.equals(newOTP)) System.out.println("Verification succeded.");
    else System.out.println("Verification failed.");

    }
}
