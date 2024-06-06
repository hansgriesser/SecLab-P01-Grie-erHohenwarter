import lombok.SneakyThrows;

import java.util.Arrays;

class Generator extends Thread {
    private volatile boolean run = true;
    private int interval;
    private int uid;
    private int length;

    public Generator(int uid, int length, int interval) {
        this.uid = uid;
        this.length = length;
        this.interval = interval;


        run = true;
    }
    @SneakyThrows
    public void run() {
        while(run) {
            String otp = null;
            long currentTime = System.currentTimeMillis() / 1000;
            try {
                otp = OTP.generateOTPUsingAES(this.uid, this.length, this.interval, currentTime);
            } catch (Exception e){
                e.printStackTrace();
            }
            if(otp == null) return;
            System.out.println(otp);
            this.sleep(1000*interval);
        }
    }

    public void kill() {
        run = false;
    }
}