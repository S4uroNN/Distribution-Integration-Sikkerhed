package opgave7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class opgave7_1 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.valutakurser.dk/");
        InputStreamReader r = new InputStreamReader(url.openStream());
        BufferedReader in = new BufferedReader(r);
        String str;
        while ((str = in.readLine()) != null) {
            if(str.contains("USD") && str.contains("DKK")){
                String sb = str.substring(str.indexOf("Amerikanske"));
                String sb2 = sb.substring(sb.indexOf("actualValue") + 13, sb.indexOf("date")-2);
                System.out.println(sb2);
            }
        }
        in.close();
    }
}
