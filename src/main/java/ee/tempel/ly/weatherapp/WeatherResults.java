package ee.tempel.ly.weatherapp;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;

import static java.lang.Math.round;

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

        this.temperature = round(Temperature-272.15);
        this.threeDayReport = ThreeDayReport;
        this.coordinates = Coordinates;
    }


    public String toString(String name) {
        return "WeatherResults "+name+"{" +"temperature=" + temperature +", threeDayReport=" + threeDayReport +", coordinates=" + coordinates +'}';
    }
}
