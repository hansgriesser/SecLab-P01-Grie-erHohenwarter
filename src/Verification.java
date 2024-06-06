import java.util.Arrays;
import java.util.Base64;

public class Verification {
public static void verifyOTP(String otp){
//        byte[] verifyOtp = Base64.getDecoder().decode(otp);

//        String verifyOTP = Arrays.toString(verifyOtp);
    String verifyOTP = otp;

//        System.out.println(verifyOTP);

        String uid = verifyOTP.substring(0, 2);
        String otpLength = verifyOTP.substring(2, 4);
        String valPer = verifyOTP.substring(4, 6);

        System.out.println("" + uid + " " + otpLength + " " + valPer);

        OTP otpgenerate = new OTP();
        String newOTP = otpgenerate.generateOTP(Integer.parseInt(uid), Integer.parseInt(otpLength), Integer.parseInt(valPer));
    System.out.println(otp + "\n" + newOTP);
    }

}
/**
 * Base64.getEncoder().encodeToString(encrypted).substring(0, OTPLength)
 */