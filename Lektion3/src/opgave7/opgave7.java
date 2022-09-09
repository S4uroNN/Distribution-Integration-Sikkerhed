package opgave7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class opgave7 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://dis.students.dk/example1.php");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str;
        while ((str = in.readLine()) != null) {
            if(!str.contains("<")) {
                for (int i = 0; i < str.length();i++){
                    if(Character.isDigit(str.charAt(i))){
                        System.out.print(str.charAt(i));
                    }
                }
            }
        }
        in.close();

    }
}
