package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sandeep.roy on 14/03/18.
 */
public class OutputRedirector extends Thread {

    InputStream is;
    String type;

    public OutputRedirector(InputStream is, String type){
        this.is = is;
        this.type = type;
    }
    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(type + "> " + line);
            }
        } catch (IOException ioE) {

        }
    }

}
