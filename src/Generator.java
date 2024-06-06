public class Generator extends Thread{
    private volatile boolean run = true;
    private int interval;
    private OTP otp = new OTP();
    public Generator(int interval) {
        this.interval = interval;
        run = true;
    }

    /**
     * Method starts the generation of an OTP and prints it out onto the console.
     */
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

    /**
     * Method kills the OTP generator.
     */
    public void kill(){
        run = false;
    }
}
