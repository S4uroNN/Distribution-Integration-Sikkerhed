package opgave6;

import java.util.Scanner;

public class SkriverThread extends Thread{
    private Common common;

    public SkriverThread(Common common){
        this.common = common;
    }
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Skriv et ord");
            String s = scan.nextLine();
            common.setIndlaest(s);
        }
    }
}
