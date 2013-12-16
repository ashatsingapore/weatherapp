package com.expedia.weatherapp.adaptor;

import com.expedia.weatherapp.util.ApplicationUtil;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;


/**
 * Customized HTTP Connector for connecting Weather App and fetching weather info.
 */
@Component
public class WeatherAdaptorImpl extends BaseHttpAdaptor implements WeatherAdaptor {

    @Value("${weather.app.service.key}")
    private String wgKey;

    @Value("${weather.app.service.uri}")
    private String serviceURI;

    @Override
    public String getWeatherDetails(String zipCode) throws MalformedURLException, IOException, RuntimeException, Exception {
        return getHttpGET(ApplicationUtil.frameURL(null,weatherAPIHost,weatherAPIPort,String.format(serviceURI,wgKey,zipCode)));
    }

    public String getWgKey() {
        return wgKey;
    }

    public void setWgKey(String wgKey) {
        this.wgKey = wgKey;
    }

    public String getServiceURI() {
        return serviceURI;
    }

    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }
}
