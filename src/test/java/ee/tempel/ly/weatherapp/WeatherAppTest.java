package ee.tempel.ly.weatherapp;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.model.SingleDayWeatherReport;
import ee.tempel.ly.weatherapp.openweatherapi.OpenWeatherApi;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;


public class WeatherAppTest {

    private WeatherResults query;

    private void assertReasonableTemperature(double temp){
        Assert.assertTrue(173 <= temp && temp <= 373);
    }

    @Before
    public void setUp() throws Exception {
        query = new WeatherApp(new OpenWeatherApi(new OpenWeatherHttpClient())).query("Tallinn");
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

        SingleDayWeatherReport[] reports = {report.getReport(0), report.getReport(1), report.getReport(2)};
        for (SingleDayWeatherReport dayReport : reports) {
            double current = dayReport.getCurrentTemperature();
            double min = dayReport.getMinTemperature();
            double max = dayReport.getMaxTemperature();
            Assert.assertTrue(min <= current && current <= max);
        }
    }

    @Test
    public void test3DayTemperatureRangesMinMaxInOrder() throws Exception {
        MultiDayWeatherReport report = query.getThreeDayReport();

        SingleDayWeatherReport[] reports = {report.getReport(0), report.getReport(1), report.getReport(2)};
        for (SingleDayWeatherReport dayReport : reports) {
            Assert.assertTrue(dayReport.getMinTemperature() <= dayReport.getMaxTemperature());
        }
    }

    @Test
    public void test3DayTemperatureRangeContainsValues() throws Exception {
        MultiDayWeatherReport report = query.getThreeDayReport();

        SingleDayWeatherReport first = report.getReport(0);
        SingleDayWeatherReport second = report.getReport(1);
        SingleDayWeatherReport third = report.getReport(2);
        Assert.assertNotNull(first);
        Assert.assertNotNull(second);
        Assert.assertNotNull(third);
    }

    @Test
    public void testCurrentTemperatureIsReasonable(){
        double temp = query.getTemperature();
        assertReasonableTemperature(temp);
    }
}
