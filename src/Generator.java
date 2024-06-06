public class Generator extends Thread{
    private volatile boolean run = true;
    private int interval;
    public Generator(int interval) {
        this.interval = interval;
        run = true;
    }

    public void run(){
        while(run){
            String otp = generateOTP();
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
    private String generateOTP(){
        // OTP generation logic
        OTP otp = new OTP();
        return otp.generateOTP(16);
    }

    public void kill(){
        run = false;
    }
}
