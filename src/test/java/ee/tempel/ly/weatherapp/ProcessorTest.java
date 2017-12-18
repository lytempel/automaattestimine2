package ee.tempel.ly.weatherapp;

import ee.tempel.ly.weatherapp.model.Coordinates;
import ee.tempel.ly.weatherapp.model.MultiDayWeatherReport;
import ee.tempel.ly.weatherapp.model.SingleDayWeatherReport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.ArrayList;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ly Tempel on 12.11.2017.
 */
public class ProcessorTest {

    @Mock WeatherApp mockApi = mock(WeatherApp.class);
    Readable input;
    @Mock Writer mockOutput = mock(Writer.class);
    WeatherResults exampleResults;

    @Before
    public void setUp() throws Exception {
        ArrayList<SingleDayWeatherReport> reports = new ArrayList<>();
        reports.add(new SingleDayWeatherReport(0,5));
        reports.add(new SingleDayWeatherReport(5,15));
        reports.add(new SingleDayWeatherReport(15,25));
        exampleResults = new WeatherResults(
                -2,
                new MultiDayWeatherReport(reports),
                new Coordinates(0, 0)
        );
        when(mockApi.query(anyString()))
                .thenReturn(exampleResults);

        input = new CharArrayReader("Tallinn\nAlaska".toCharArray());

    }



    @Test
    public void testProcessResultCorrect() throws Exception {
        StringWriter out = new StringWriter();
        new Processor(mockApi).process(input, out);
        Assert.assertEquals(exampleResults.toString("Tallinn")+"\n"+exampleResults.toString("Alaska")+"\n", out.toString());

    }

}