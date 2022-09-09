package opgave8;

import java.util.ArrayList;
import java.util.List;

public class SlutThread extends Thread{
    private ArrayList<Integer> input;
    private int start;
    private int slut;

    public SlutThread(ArrayList<Integer> liste, int start, int slut) {
        this.input = liste;
        this.start = start;
        this.slut = slut;
    }

    @Override
    public void run() {
        mergesort(input,start,slut);
    }
    public void mergesort(List<Integer> list, int l, int h){
        if (l<h) {
            int m = (l + h) / 2;
            mergesort(list,l,m);
            mergesort(list,m+1,h);
            merge(list,l,m,h);
        }
    }

    // kombiner er realiseret ved fletteskabelonen
    private void merge(List<Integer> list,int low, int middle, int high){
        List<Integer> temp = new ArrayList<Integer>();
        int i = low;
        int j = middle+1;
        while (i<=middle && j<=high){
            if (list.get(i).compareTo(list.get(j))<=0){
                temp.add(list.get(i));
                i++;
            }
            else {
                temp.add(list.get(j));
                j++;
            }

        }
        while(i<=middle){
            temp.add(list.get(i));
            i++;
        }
        while(j<=high){
            temp.add(list.get(j));
            j++;
        }
        for (int k=0; k<temp.size();k++){
            list.set(low+k,temp.get(k));
        }
    }
}
