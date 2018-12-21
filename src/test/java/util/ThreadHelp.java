package util;

public class ThreadHelp {

    private static final long DEFAULT_SLEEP_TIME = 100;

    public static void sleep(){
        try {
            Thread.sleep(DEFAULT_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
