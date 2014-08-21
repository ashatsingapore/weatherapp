package com.expedia.weatherapp.controller;

import com.expedia.weatherapp.constants.ApplicationConstants;
import com.expedia.weatherapp.model.WeatherModel;
import com.expedia.weatherapp.model.WunderGroundModel;
import com.expedia.weatherapp.service.WeatherService;
import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Locale;


/**
 * Weather App Controller for invoking service class and customizing error messages
 */
@Controller
@RequestMapping(value = "/")
public class WeatherAppController {

    @Qualifier("messageSource")
    @Autowired
    private MessageSource localeMessageSource;

    @Autowired
    private WeatherService weatherService;

    /**
     * Home context for loading the Search page
     * @param model Model object
     * @return Home page context
     */
    @RequestMapping(value = {"/", "", "/home"}, method = RequestMethod.GET)
         public String landingPage(Model model) {
        return "home";
    }

    /**
     * Call for fetching the Zip code
     * Valid annotations can also be used for validation of empty value check & length of the String
     *
     * @param zipCode Valid US Zip code
     * @return Weather
     */
    @RequestMapping(value = "/zipcode.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> getWeatherByZip(@RequestParam("zipCode") String zipCode) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        try {
            if(zipCode.length() != 5) {
                return new ResponseEntity<String>(String.format(ApplicationConstants.ACK_FAILURE_MESSAGE_JSON, getInvalidZip()), responseHeaders,  HttpStatus.BAD_REQUEST);
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
            String resp = mapper.writeValueAsString(weatherService.getWeatherByZip(zipCode));

            if(null == resp || resp.equalsIgnoreCase("null")) {
                return new ResponseEntity<String>(String.format(ApplicationConstants.ACK_FAILURE_MESSAGE_JSON, getUnavailableZip()), responseHeaders,  HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<String>(String.format(ApplicationConstants.ACK_SUCCESS_MESSAGE_JSON, resp), responseHeaders,  HttpStatus.OK);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<String>(String.format(ApplicationConstants.ACK_FAILURE_MESSAGE_JSON, getServiceError()), responseHeaders,  HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity<String>(String.format(ApplicationConstants.ACK_FAILURE_MESSAGE_JSON, getServiceError()), responseHeaders,  HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    public String getInvalidZip(){
        return getLocalizedMessage(ApplicationConstants.INVALID_ZIP);
    }

    public String getUnavailableZip(){
        return getLocalizedMessage(ApplicationConstants.UNAVAILABLE_ZIP);
    }

    public String getServiceError(){
        return getLocalizedMessage(ApplicationConstants.SERVICE_ERROR);
    }

    private String getLocalizedMessage(String messageKey){
        return localeMessageSource.getMessage(messageKey, null, null);
    }
}
