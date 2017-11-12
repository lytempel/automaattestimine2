package ee.tempel.ly.weatherapp;

import ee.tempel.ly.weatherapp.openweatherapi.OpenWeatherApi;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;

import java.io.*;

/**
 * Created by Ly Tempel on 12.11.2017.
 */
public class Main {

    private static WeatherApp weatherApp = new WeatherApp(new OpenWeatherApi(new OpenWeatherHttpClient()));

    public static void main(String[] args) throws IOException {
        processInteractively();
        // processFiles();
    }

    private static void processInteractively() throws IOException {
        OutputStreamWriter output = new OutputStreamWriter(System.out);
        new Processor(weatherApp).process(new InputStreamReader(System.in), output);
    }
    private static void processFiles() throws IOException {
        FileReader in = new FileReader(new File("input.txt"));
        FileWriter out = new FileWriter(new File("output.txt"));
        new Processor(weatherApp).process(in, out);
    }
}
