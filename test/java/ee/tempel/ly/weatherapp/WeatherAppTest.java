package ee.tempel.ly.weatherapp;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.model.SingleDayWeatherReport;
import ee.tempel.ly.weatherapp.util.Constants;

public class WeatherAppTest {

    private WeatherResults query;

    private void assertReasonableTemperature(double temp){
        Assert.assertTrue(-100 <= temp && temp <= 100);
    }

    @Before
    public void setUp() throws Exception {
        query = new WeatherApp().query("Tallinn", "EE", Constants.METRIC);
    }

    @Test
    public void testCoordinatesOnGlobe() throws Exception {
        Coordinates location =  query.getCoordinates();
        double lon = location.getLongitude();
        double lat = location.getLatitude();
        Assert.assertTrue(-90 <= lat && lat <= 90);
        Assert.assertTrue(-180 <= lon && lon <= 180);
    }

    @Test
    public void testCoordinatesRoughlyCorrect() throws Exception {
        Coordinates location =  query.getCoordinates();
        Assert.assertEquals(24.0, location.getLongitude(), 1.0);
        Assert.assertEquals(59.0, location.getLatitude(), 1.0);
    }

    @Test
    public void test3DayTemperaturesInReportedRange() throws Exception {
        MultiDayWeatherReport report = query.getThreeDayReport();

        SingleDayWeatherReport[] reports = {report.getDay(0), report.getDay(1), report.getDay(2)};
        for (SingleDayWeatherReport dayReport : reports) {
            double current = dayReport.getCurrentTemperatures();
            double min = dayReport.getMinTemperature();
            double max = dayReport.getMaxTemperature();
            Assert.assertTrue(min <= current && current <= max);
        }
    }

    @Test
    public void test3DayTemperatureRangesMinMaxInOrder() throws Exception {
        MultiDayWeatherReport report = query.getThreeDayReport();

        SingleDayWeatherReport[] reports = {report.getDay(0), report.getDay(1), report.getDay(2)};
        for (SingleDayWeatherReport dayReport : reports) {
            Assert.assertTrue(dayReport.getMinTemperature() <= dayReport.getMaxTemperature());
        }
    }

    @Test
    public void test3DayTemperatureRangeContainsValues() throws Exception {
        MultiDayWeatherReport report = query.getThreeDayReport();

        SingleDayWeatherReport first = report.getDay(0);
        SingleDayWeatherReport second = report.getDay(1);
        SingleDayWeatherReport third = report.getDay(2);
        Assert.assertNotNull(first);
        Assert.assertNotNull(second);
        Assert.assertNotNull(third);
    }

    @Test
    public void testCurrentTemperatureMatchesInBothUnits() throws Exception {
        double tempC = query.getTemperature();
        double tempF = new WeatherApp()
                .query("Tallinn", "EE", Constants.IMPERIAL)
                .getTemperature();

        double expected = (tempC * 1.8) + 32;
        double min = expected - 2;
        double max = expected + 2;
        Assert.assertTrue(min <= tempF && tempF <= max);

    }

    @Test
    public void testCurrentTemperatureIsReasonable(){
        double temp = query.getTemperature();
        assertReasonableTemperature(temp);
    }
}
