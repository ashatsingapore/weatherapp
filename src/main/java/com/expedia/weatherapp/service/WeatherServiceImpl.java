package com.expedia.weatherapp.service;

import com.expedia.weatherapp.adaptor.WeatherAdaptor;
import com.expedia.weatherapp.model.WeatherModel;
import com.expedia.weatherapp.model.WunderGroundModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherAdaptor weatherAdaptor;

    @Autowired
    private ConversionService conversionService;

    @Override
    public WeatherModel getWeatherByZip(String zipCode) throws MalformedURLException, IOException, RuntimeException, Exception {
        WunderGroundModel model = getConversionService().convert(weatherAdaptor.getWeatherDetails(zipCode), WunderGroundModel.class);
        return getConversionService().convert(model, WeatherModel.class);
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public WeatherAdaptor getWeatherAdaptor() {
        return weatherAdaptor;
    }

    public void setWeatherAdaptor(WeatherAdaptor weatherAdaptor) {
        this.weatherAdaptor = weatherAdaptor;
    }
}
