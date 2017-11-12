package ee.tempel.ly.weatherapp.openweatherapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import ee.tempel.ly.weatherapp.WeatherApi;
import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.CurrentWeatherReport;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.model.SingleDayWeatherReport;
import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherCurrentWeatherResponse;
import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherForecast;
import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherForecastResponse;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;
import ee.tempel.ly.weatherapp.util.Constants;

public class OpenWeatherApi implements WeatherApi {
    OpenWeatherHttpClient httpClient;
    public OpenWeatherApi(OpenWeatherHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CurrentWeatherReport getCurrent(String city, String countryCode) throws IOException {
        OpenWeatherCurrentWeatherResponse response = httpClient.getCurrentWeather(city, countryCode);
        return new CurrentWeatherReport(response.main.temp, response.name, response.sys.country,
                new Coordinates(response.coord.lat, response.coord.lon));
    }

    public MultiDayWeatherReport getForecast(String city, String countryCode) throws IOException {
        OpenWeatherForecastResponse response = httpClient.getForecast(city, countryCode);


        return new MultiDayWeatherReport(
                response.list.stream()
                    .map(f -> new SingleDayWeatherReport(f.main.temp_min, f.main.temp_max, f.main.temp))
                    .collect(Collectors.toList())
        );
    }



}
