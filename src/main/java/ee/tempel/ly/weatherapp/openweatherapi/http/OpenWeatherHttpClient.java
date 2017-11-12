package ee.tempel.ly.weatherapp.openweatherapi.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.MessageFormat;

import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherCurrentWeatherResponse;
import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherForecastResponse;

import static ee.tempel.ly.weatherapp.openweatherapi.util.OpenWeatherConstants.API_KEY;

public class OpenWeatherHttpClient {

    public OpenWeatherCurrentWeatherResponse getCurrentWeather(String city) throws IOException {

        String url = MessageFormat.format("http://api.openweathermap.org/data/2.5/weather?APPID={0}&q={1}", API_KEY, city);
        Reader reader = new InputStreamReader(new URL(url).openStream());

        Gson gson = new Gson();
        return gson.fromJson(reader, OpenWeatherCurrentWeatherResponse.class);
    }
    public OpenWeatherForecastResponse getForecast(String city) throws IOException {

        String url = MessageFormat.format("http://api.openweathermap.org/data/2.5/forecast?APPID={0}&q={1}", API_KEY, city);
        Reader reader = new InputStreamReader(new URL(url).openStream());

        Gson gson = new Gson();
        return gson.fromJson(reader, OpenWeatherForecastResponse.class);
    }


}
