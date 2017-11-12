package ee.tempel.ly.weatherapp;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;

public class WeatherResults {
    double temperature;
    MultiDayWeatherReport threeDayReport;
    Coordinates coordinates;

    public double getTemperature() {
        return temperature;
    }

    public MultiDayWeatherReport getThreeDayReport() {
        return threeDayReport;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public WeatherResults(double Temperature, MultiDayWeatherReport ThreeDayReport, Coordinates Coordinates) {

        this.temperature = Temperature;
        this.threeDayReport = ThreeDayReport;
        this.coordinates = Coordinates;
    }
}
