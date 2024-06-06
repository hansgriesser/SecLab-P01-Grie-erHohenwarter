public class Generator extends Thread{
    private volatile boolean run = true;
    private int uid;
    private int otpLength;
    private int valPer;
    private OTP otp;
    long sysTime;

    public Generator(int uid, int otpLength, int valPer) {
        otp  = new OTP();
        this.uid = uid;
        this.otpLength = otpLength;
        this.valPer = valPer;
        run = true;
    }

    /**
     * Method starts the generation of an OTP and prints it out onto the console.
     */
    public void run(){
        while(run){
            this.sysTime = getSysTime();
            String otp = this.otp.generateOTP(this.uid, this.otpLength, this.valPer, this.sysTime);
            if(otp == null){
                System.out.println("Error.");
            }
            System.out.println(sysTime);
            System.out.println(otp);
            Verification.verifyOTP(otp, this.sysTime);
            try {
                Thread.sleep(1000 * valPer);
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

    /**
     * Returns system time when the OTP is being generated.
     * @return long
     */
    private long getSysTime(){
        return System.currentTimeMillis() / 1000;
    }
}
