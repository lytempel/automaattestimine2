package ee.tempel.ly.weatherapp.openweatherapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

public class OpenWeatherApi implements WeatherApi {
    OpenWeatherHttpClient httpClient;
    public OpenWeatherApi(OpenWeatherHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CurrentWeatherReport getCurrent(String city) throws IOException {
        OpenWeatherCurrentWeatherResponse response = httpClient.getCurrentWeather(city);
        return new CurrentWeatherReport(response.main.temp, response.name, response.sys.country,
                new Coordinates(response.coord.lat, response.coord.lon));
    }

    private int getUnixDays(OpenWeatherForecast forecast){
        return forecast.dt / (60*60*24); // Note: dt is in seconds
    }

    public MultiDayWeatherReport getForecast(String city) throws IOException {
        OpenWeatherForecastResponse response = httpClient.getForecast(city);

        Map<Integer, List<OpenWeatherForecast>> forecastsByDay = response.list.stream()
                .collect(Collectors.groupingByConcurrent(fc -> (Integer) OpenWeatherApi.this.getUnixDays(fc)));
        return new MultiDayWeatherReport(
                forecastsByDay.keySet().stream()
                        .sorted()
                        .map(i -> new SingleDayWeatherReport(
                                        forecastsByDay.get(i).stream().mapToDouble(fc -> fc.main.temp_min).min().getAsDouble(),
                                        forecastsByDay.get(i).stream().mapToDouble(fc -> fc.main.temp_max).max().getAsDouble(),
                                        forecastsByDay.get(i).stream().mapToDouble(fc -> fc.main.temp).average().getAsDouble())
                                // Note: Average temperature of the day is not exactly current ↑
                        )
                        .limit(3) // Note: OpenWeather returns 5 days of forecast
                        .collect(Collectors.toList())
        );
    }

}
