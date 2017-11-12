package ee.tempel.ly.weatherapp.model;

public interface MultiDayWeatherReport {
    SingleDayWeatherReport getDay(int i);
}
