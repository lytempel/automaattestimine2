package ee.tempel.ly.weatherapp;

import java.io.IOException;

import ee.tempel.ly.weatherapp.model.CurrentWeatherReport;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.util.Constants;

public interface WeatherApi {
    MultiDayWeatherReport getForecast(String city, String countryCode, Constants.Unit unit) throws IOException;
    CurrentWeatherReport getCurrent(String city, String countryCode, Constants.Unit unit) throws IOException;
}
