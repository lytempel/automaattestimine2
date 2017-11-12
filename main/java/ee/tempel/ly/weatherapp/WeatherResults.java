package ee.tempel.ly.weatherapp;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;

public interface WeatherResults {
    double getTemperature();
    MultiDayWeatherReport getThreeDayReport();
    Coordinates getCoordinates();
}
