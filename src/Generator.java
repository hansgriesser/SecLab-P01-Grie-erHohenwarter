public class Generator extends Thread{
    private volatile boolean run = true;
    private int uid;
    private int otpLength;
    private int interval;
    private long sysTime;
    private OTP otp;
    public Generator(int uid, int otpLength, int interval) {
        otp  = new OTP();
        this.uid = uid;
        this.otpLength = otpLength;
        this.interval = interval;
        this.sysTime = System.currentTimeMillis();
        run = true;
    }

    /**
     * Method starts the generation of an OTP and prints it out onto the console.
     */
    public void run(){
        while(run){
            String otp = this.otp.generateOTP(this.uid, this.otpLength, this.interval, this.sysTime);
            if(otp == null){
                System.out.println("Error.");
            }
            System.out.println(otp);
            Verification.verifyOTP(otp, this.sysTime);
            try {
                Thread.sleep(1000 * interval);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method kills the OTP generator.
     */
    public void kill(){
        run = false;
    }
}
