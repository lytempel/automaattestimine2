package ee.tempel.ly.weatherapp.model;

import static java.lang.Math.round;

public final class SingleDayWeatherReport {
    private final double minTemperature;
    private final double maxTemperature;

    public SingleDayWeatherReport(double minTemperature, double maxTemperature) {
        this.minTemperature = round(minTemperature-272.15);
        this.maxTemperature = round(maxTemperature-272.15);
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }


    @Override
    public String toString() {
        return  "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ';' + ' ';
    }
}
