import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        int uid = 2;
        int otpLegth = 16;
        int valPer = 1;
        Generator countDown = new Generator(uid, otpLegth, valPer);
        countDown.run();

        if(countDown != null){
            countDown.kill();
        }
        countDown = null;
    }
}
