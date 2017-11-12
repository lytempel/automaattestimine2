package ee.tempel.ly.weatherapp;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 * Created by Ly Tempel on 12.11.2017.
 */
public class Processor {
    WeatherApp api;

    public Processor(WeatherApp api) {
        this.api = api;
    }

    public void process(Readable input, Writer output) throws IOException {
        Scanner in = new Scanner(input);
        while(in.hasNext()){
            String city = in.nextLine();
            output.append(api.query(city).toString(city));
            output.append("\n");
            output.flush();
        }
        output.close();
    }
}
