package ee.tempel.ly.weatherapp.openweatherapi.apimodel;

import java.util.List;

public class OpenWeatherForecastResponse {
    public int cod;
    public double message;
    public int cnt;
    public List<OpenWeatherForecast> list;
    public City city;

    public class City {
        public class Coords {
            public double lat;
            public double lon;
        }

        public int id;
        public String name;
        public Coords coord;
        public String country;

    }
}
