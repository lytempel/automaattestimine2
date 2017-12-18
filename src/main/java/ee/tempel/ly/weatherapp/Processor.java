package ee.tempel.ly.weatherapp;

import java.io.File;
import java.io.FileWriter;
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


    public void processIntoSeparateFiles(Readable input) throws IOException {
        Scanner in = new Scanner(input);
        while(in.hasNext()){
            String city = in.nextLine();
            FileWriter writer = new FileWriter(new File(city + ".txt"));
            writer.append(api.query(city).toString(city));
            writer.append("\n");
            writer.flush();
            writer.close();
        }
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
