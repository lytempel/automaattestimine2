package ee.tempel.ly.weatherapp.openweatherapi.apimodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherForecast {
    public int dt;
    public Main main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public Prec rain;
    public Prec snow;
    public Sys sys;
    public String dt_txt;


    public class Main {
        public double temp;
        public double temp_min;
        public double temp_max;
        public double pressure;
        public double sea_level;
        public double grnd_level;
        public double humidity;
        public double temp_kf;
    }
    public class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    public class Clouds {
        public int all;
    }
    public class Wind {
        public double speed;
        public double dog;
    }
    public class Prec {
        @SerializedName("3h")
        public double mil;
    }

    public static class Sys {
        public String pod;
    }
}
