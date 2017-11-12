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

    public WeatherResults query(String city, String countryCode) throws IOException {
        openWeatherApi = new OpenWeatherApi(new OpenWeatherHttpClient());
        MultiDayWeatherReport forecast = openWeatherApi.getForecast(city, countryCode);
        CurrentWeatherReport weather = openWeatherApi.getCurrent(city, countryCode);
        return new WeatherResults(weather.getCurrentTemperature(), forecast, weather.getCoordinates());
    }

    public static void main(String[] args) throws IOException {
        WeatherResults res = new WeatherApp(new OpenWeatherApi(new OpenWeatherHttpClient())).query("Tallinn", "ee");
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
            System.out.println(res.getThreeDayReport().getMinForDay(i));
            System.out.print("Max: ");
            System.out.println(res.getThreeDayReport().getMaxForDay(i));
        }

    }
}
