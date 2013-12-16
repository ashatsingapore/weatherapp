package com.expedia.weatherapp.util;

import com.expedia.weatherapp.constants.ApplicationConstants;
import org.springframework.stereotype.Component;

/**
 * Common conversions across application
 */
@Component
public class ApplicationUtil {

    // Custom Integer Parser with default value as fallback
    public static Integer parseInt(String strVal, int defaultVal) {
        try {
            return Integer.parseInt(strVal);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    // Frame URL
    public static String frameURL(String protocol, String hostname, String port, String uri) {
        StringBuilder connectionURL = new StringBuilder();
        connectionURL.append((null!=protocol)?protocol:ApplicationConstants.SERVICE_PROTOCOL)
                .append(ApplicationConstants.PROTOCOL_DELIMITER)
                .append((null != hostname) ? hostname : ApplicationConstants.LOCAL_HOST)
                .append(ApplicationConstants.PORT_DELIMITER)
                .append((null != port) ? port : ApplicationConstants.DEFAULT_PORT)
                .append((null != uri) ? ApplicationConstants.URI_DELIMITER + uri : "");

        return connectionURL.toString();
    }
}
