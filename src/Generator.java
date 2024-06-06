import lombok.SneakyThrows;

public class Generator extends Thread{
    private volatile boolean run = true;
    private int interval;
    private OTP generateOTP;
    public Generator(int interval){
        this.interval = interval;
        run = true;
    }

    @SneakyThrows
    public void run(){
        while(run){
            String otp;
            otp = this.generateOTP.run();
            if(otp == null) return;
            this.sleep(1000 * interval);
        }
    }

    public void kill(){
        run = false;
    }
}
