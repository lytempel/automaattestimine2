package ee.tempel.ly.weatherapp.model;

import static java.lang.Math.round;

public final class SingleDayWeatherReport {
    private final double minTemperature;
    private final double maxTemperature;
    private final double currentTemperature;

    public SingleDayWeatherReport(double minTemperature, double maxTemperature, double currentTemperature) {
        this.minTemperature = round(minTemperature-272.15);
        this.maxTemperature = round(maxTemperature-272.15);
        this.currentTemperature = round(currentTemperature-272.15);
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public String toString() {
        return  "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", currentTemperature=" + currentTemperature +
                ';' + ' ';
    }
}
