public class Generator extends Thread{
    private volatile boolean run = true;
    private int interval;
    private OTP otp = new OTP();
    public Generator(int interval) {
        this.interval = interval;
        run = true;
    }

    public void run(){
        while(run){
            String otp = this.otp.generateOTP(16);
            if(otp == null){
                System.out.println("Error.");
            }
            System.out.println(otp);
            try {
                Thread.sleep(1000 * interval);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void kill(){
        run = false;
    }
}
