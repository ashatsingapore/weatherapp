package com.expedia.weatherapp.adaptor;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Weather Adaptor Interface
 */
@Component
public interface WeatherAdaptor {
    public String getWeatherDetails(String zipCode) throws MalformedURLException, IOException, RuntimeException, Exception;
}
