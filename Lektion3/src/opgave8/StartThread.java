package opgave8;

import java.util.ArrayList;

public class StartThread extends Thread{
    private ArrayList<Integer> input;
    private int start;
    private int slut;

    public StartThread(ArrayList<Integer> liste, int start, int slut){
        this.input = liste;
        this.start = start;
        this.slut = slut;
    }

    @Override
    public void run() {
        mergesort(input,start,slut);
    }

    public void mergesort(ArrayList<Integer> liste, int start, int slut){

    }
}
