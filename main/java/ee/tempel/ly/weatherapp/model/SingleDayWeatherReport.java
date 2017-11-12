package ee.tempel.ly.weatherapp.model;

public final class SingleDayWeatherReport {
    private final double minTemperature;
    private final double maxTemperature;
    private final double currentTemperature;

    public SingleDayWeatherReport(double minTemperature, double maxTemperature, double currentTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.currentTemperature = currentTemperature;
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
}
