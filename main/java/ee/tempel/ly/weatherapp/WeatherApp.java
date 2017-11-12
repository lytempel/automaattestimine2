package ee.tempel.ly.weatherapp;

import java.io.IOException;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.CurrentWeatherReport;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.model.SingleDayWeatherReport;
import ee.tempel.ly.weatherapp.openweatherapi.OpenWeatherApi;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;
import ee.tempel.ly.weatherapp.util.Constants.Unit;

import static ee.tempel.ly.weatherapp.util.Constants.Unit.METRIC;

public class WeatherApp {

    private WeatherApi openWeatherApi;

    public WeatherApp(WeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    public WeatherResults query(String city, String countryCode, Unit unit) throws IOException {
        openWeatherApi = new OpenWeatherApi(new OpenWeatherHttpClient());
        MultiDayWeatherReport forecast = openWeatherApi.getForecast(city, countryCode, unit);
        CurrentWeatherReport weather = openWeatherApi.getCurrent(city, countryCode, unit);
        return new WeatherResults(weather.getCurrentTemperature(), forecast, weather.getCoordinates());
    }

    public static void main(String[] args) throws IOException {
        WeatherResults res = new WeatherApp(new OpenWeatherApi(new OpenWeatherHttpClient())).query("Tallinn", "ee", METRIC);
        System.out.print("Coordinates: ");
        System.out.print(res.getCoordinates().getLatitude());
        System.out.print("  ,   ");
        System.out.println(res.getCoordinates().getLongitude());

        System.out.print("Temperature: ");
        System.out.println(res.getTemperature());
        System.out.println("Individual days (?) (hours?)");
        for (int i = 0; i < 3; i++) {
            System.out.println("Day "+i);
            System.out.print("Min: ");
            System.out.println(res.getThreeDayReport().getDay(i).stream().mapToDouble(r -> r.getMinTemperature()).min().getAsDouble());
            System.out.print("Max: ");
            System.out.println(res.getThreeDayReport().getDay(i).stream().mapToDouble(r -> r.getMaxTemperature()).max().getAsDouble());
        }

    }
}
