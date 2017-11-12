package ee.tempel.ly.weatherapp.model;

public class CurrentWeatherReport {
    private final double currentTemperature;
    private final String city;
    private final String countryCode;
    private final Coordinates coordinates;

    public CurrentWeatherReport(double currentTemperature, String city, String countryCode, Coordinates coordinates) {
        this.currentTemperature = currentTemperature;
        this.city = city;
        this.countryCode = countryCode;
        this.coordinates = coordinates;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
