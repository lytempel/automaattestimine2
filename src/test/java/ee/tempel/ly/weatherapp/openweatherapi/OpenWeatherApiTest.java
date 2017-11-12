package ee.tempel.ly.weatherapp.openweatherapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ee.tempel.ly.weatherapp.openweatherapi.apimodel.OpenWeatherCurrentWeatherResponse;
import ee.tempel.ly.weatherapp.openweatherapi.http.OpenWeatherHttpClient;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherApiTest {


    private OpenWeatherHttpClient httpClient = Mockito.mock(OpenWeatherHttpClient.class);
    private OpenWeatherApi api = new OpenWeatherApi(httpClient);


    @Test
    public void getCurrent() throws Exception {
        when(httpClient.getCurrentWeather(anyString()))
                .thenReturn(new OpenWeatherCurrentWeatherResponse());
    }

    @Test
    public void getForecast() throws Exception {
    }

}
