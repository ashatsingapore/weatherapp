package com.expedia.weatherapp;

import com.expedia.weatherapp.adaptor.WeatherAdaptorImpl;
import com.expedia.weatherapp.model.Current_observation;
import com.expedia.weatherapp.model.Display_location;
import com.expedia.weatherapp.model.WunderGroundModel;
import com.expedia.weatherapp.service.WeatherServiceImpl;
import com.expedia.weatherapp.util.JsonObjConverter;
import com.expedia.weatherapp.util.WeatherCustomConverter;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-test.xml"})
public class WeatherAppTest {

    @Spy
    private WeatherServiceImpl weatherService;

    @Mock
    private WeatherAdaptorImpl weatherAdaptor;

    @Mock
    private WunderGroundModel model;

    protected GenericConversionService conversionService = new GenericConversionService();

    @Before
    public void setup() {
        weatherService = new WeatherServiceImpl();
        MockitoAnnotations.initMocks(this);
        weatherService.setWeatherAdaptor(weatherAdaptor);
        model = new WunderGroundModel();
        conversionService.addConverter(new JsonObjConverter());
        conversionService.addConverter(new WeatherCustomConverter());
    }

    private void baseConfig() throws Exception {
        //doThrow(new Exception()).when(weatherAdaptor).getWeatherDetails((String) anyObject());
        when(weatherService.getConversionService()).thenReturn(conversionService);
    }

    @Test
    public void testValidZip() throws Exception {
        baseConfig();
        Current_observation observation = new Current_observation();
        Display_location location = new Display_location();
        location.setCity("Los Angeles");
        location.setState("CA");
        observation.setTemp_f(70.5);
        observation.setDisplay_location(location);
        model.setCurrent_observation(observation);
        ObjectMapper mapper = new ObjectMapper();
        when(weatherAdaptor.getWeatherDetails((String) anyObject())).thenReturn(mapper.writeValueAsString(model));
        Assert.assertEquals(weatherService.getWeatherByZip("90001").getCity(), "Los Angeles");
    }

    @Test (expected = NullPointerException.class)
    public void testInValidZip() throws Exception {
        baseConfig();
        Current_observation observation = new Current_observation();
        Display_location location = new Display_location();
        observation.setDisplay_location(location);
        model.setCurrent_observation(observation);
        ObjectMapper mapper = new ObjectMapper();
        when(weatherAdaptor.getWeatherDetails((String) anyObject())).thenReturn(mapper.writeValueAsString(model));
        Assert.assertNull(weatherService.getWeatherByZip("88888").getCity());
    }
}
