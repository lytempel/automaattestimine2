package ee.tempel.ly.weatherapp.openweatherapi.apimodel;


import ee.tempel.ly.weatherapp.model.Coordinates;

public class OpenWeatherCurrentWeatherResponse {
    public Coords coord;
    public Main main;
    public Sys sys;
    public String name;

    public class Sys {
        public String country;
    }
    public class Coords {
        public double lat;
        public double lon;
    }
    public class Main {
        public double temp;
        public double temp_min;
        public double temp_max;
        public double pressure;
        public double humidity;
    }
}
