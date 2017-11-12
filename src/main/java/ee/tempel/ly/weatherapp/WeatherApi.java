package ee.tempel.ly.weatherapp;

import java.io.IOException;

import ee.tempel.ly.weatherapp.model.CurrentWeatherReport;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;

public interface WeatherApi {
    MultiDayWeatherReport getForecast(String city) throws IOException;
    CurrentWeatherReport getCurrent(String city) throws IOException;
}
