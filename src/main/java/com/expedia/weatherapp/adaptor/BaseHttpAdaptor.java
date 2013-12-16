package com.expedia.weatherapp.adaptor;

import com.expedia.weatherapp.constants.ApplicationConstants;
import com.expedia.weatherapp.util.ApplicationUtil;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

/**
 * Base HTTP Connector to initiate the Pool Connection Manager and configure the basic setup
 */
@Component
public class BaseHttpAdaptor implements ApplicationAdaptor {

    protected static CloseableHttpClient httpClient;

    @Value("${weather.app.service.host}")
    protected String weatherAPIHost;

    @Value("${weather.app.service.port}")
    protected String weatherAPIPort;

    /**
     * Initialize method to create HTTPClient Connection pool
     */
    @PostConstruct
    public void initialize() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(ApplicationConstants.HTTP_MAX_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(ApplicationConstants.ROUTE_MAX_CONNECTIONS);

        int port = ApplicationUtil.parseInt(weatherAPIPort, ApplicationConstants.DEFAULT_PORT);

        //Register all hosts here
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(weatherAPIHost, port)), ApplicationConstants.ROUTE_MAX_CONNECTIONS);

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }


    /**
     * Makes an HTTP GET call and returns the response content
     * @param url - URL for making a GET request
     * @return
     * @throws Exception
     */
    public String getHttpGET(String url) throws MalformedURLException, IOException, RuntimeException, Exception {
        HttpGet getMethod = new HttpGet(url);
        String responseBody = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
            if (httpResponse.getStatusLine().getStatusCode() != ApplicationConstants.STATUS_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpResponse.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (httpResponse.getEntity().getContent())));
            String output;
            while ((output = br.readLine()) != null) {
                responseBody += output;
            }
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            getMethod.releaseConnection();
        }
        return responseBody;
    }

    public String getWeatherAPIHost() {
        return weatherAPIHost;
    }

    public void setWeatherAPIHost(String weatherAPIHost) {
        this.weatherAPIHost = weatherAPIHost;
    }

    public String getWeatherAPIPort() {
        return weatherAPIPort;
    }

    public void setWeatherAPIPort(String weatherAPIPort) {
        this.weatherAPIPort = weatherAPIPort;
    }

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static void setHttpClient(CloseableHttpClient httpClient) {
        BaseHttpAdaptor.httpClient = httpClient;
    }
}
