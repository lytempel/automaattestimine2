package ee.tempel.ly.weatherapp;

import java.io.IOException;

import ee.tempel.ly.weatherapp.model.CurrentWeatherReport;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.openweatherapi.OpenWeatherApi;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;


public class WeatherApp {

    private WeatherApi openWeatherApi;

    public WeatherApp(WeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    public WeatherResults query(String city) throws IOException {
        openWeatherApi = new OpenWeatherApi(new OpenWeatherHttpClient());
        MultiDayWeatherReport forecast = openWeatherApi.getForecast(city);
        CurrentWeatherReport weather = openWeatherApi.getCurrent(city);
        return new WeatherResults(weather.getCurrentTemperature(), forecast, weather.getCoordinates());
    }

}
