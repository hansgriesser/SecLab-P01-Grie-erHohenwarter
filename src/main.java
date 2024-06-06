import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        int interval = 10;
        Generator countdown;
        countdown = new Generator(interval);
        countdown.start();
        System.out.println();

        if(countdown != null){
            countdown.kill();
        }
        countdown = null;
    }
}
