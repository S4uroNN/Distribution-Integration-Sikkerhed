package opgave6;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Common common = new Common();

        SkriverThread skriverThread = new SkriverThread(common);
        UdskriverThread udskriverThread = new UdskriverThread(common);

        skriverThread.start();
        udskriverThread.start();

//        skriverThread.join();
//        udskriverThread.join();

    }
}
