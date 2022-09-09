package opgave6;

public class UdskriverThread extends Thread {
    private Common common;


    public UdskriverThread(Common common){
        this.common = common;
    }

    public void run(){
        try {

            while(true){
                this.sleep(1000);
                System.out.println(common.getIndlaest());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}


