import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        int uid = 22;
        int otpLegth = 16;
        int interval = 10;
        Generator countDown = new Generator(uid, otpLegth, interval);
        countDown.run();

        if(countDown != null){
            countDown.kill();
        }
        countDown = null;
    }
}
