package com.expedia.weatherapp.service;

import com.expedia.weatherapp.model.WeatherModel;
import com.expedia.weatherapp.model.WunderGroundModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public interface WeatherService {
    public WeatherModel getWeatherByZip(String zipCode) throws MalformedURLException, IOException, RuntimeException, Exception;
}
