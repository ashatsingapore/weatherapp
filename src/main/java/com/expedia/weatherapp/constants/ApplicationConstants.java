package com.expedia.weatherapp.constants;

public interface ApplicationConstants {
    // Default values
    public static final int HTTP_MAX_CONNECTIONS = 100;
    public static final int ROUTE_MAX_CONNECTIONS = 10;
    public static final String LOCAL_HOST = "127.0.0.1";
    public static final int DEFAULT_PORT = 80;
    public static final int STATUS_OK = 200;
    public static final String SERVICE_PROTOCOL = "http";
    //Delimiters
    public static final String PROTOCOL_DELIMITER = "://";
    public static final String PORT_DELIMITER = ":";
    public static final String URI_DELIMITER = "/";

    //Response Format
    public static final String ACK_SUCCESS_MESSAGE_JSON = "{\"success\":true, \"msg\": %s}";
    public static final String ACK_FAILURE_MESSAGE_JSON = "{\"success\":false, \"msg\": \"%s\"}";

    // Error Message Keys
    public static final String INVALID_ZIP = "weather.zip.invalid";
    public static final String UNAVAILABLE_ZIP = "weather.zip.unavailable";
    public static final String SERVICE_ERROR = "weather.service.failure";

    //Others
    public static final String FAHRENHEIT = "F";
}
