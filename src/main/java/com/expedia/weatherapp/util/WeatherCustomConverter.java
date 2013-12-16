package com.expedia.weatherapp.util;

import com.expedia.weatherapp.constants.ApplicationConstants;
import com.expedia.weatherapp.model.WeatherModel;
import com.expedia.weatherapp.model.WunderGroundModel;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This utility class is for creating the Custom Weather Object
 */
public class WeatherCustomConverter implements GenericConverter {


    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairs = new HashSet<ConvertiblePair>();

        pairs.add(new ConvertiblePair(WunderGroundModel.class, WeatherModel.class));

        return pairs;
    }

    /**
     * Convert WunderGroundModel to custom Ob
     *
     * @param source - Source Object
     * @param sourceDescriptor - Source Type
     * @param targetDescriptor - Target Type
     * @return
     */
    @Override
    public Object convert(Object source, TypeDescriptor sourceDescriptor,
                          TypeDescriptor targetDescriptor) {

        if(source instanceof WunderGroundModel)  {
            WeatherModel model = null;
            try {
                WunderGroundModel sourceModel = (WunderGroundModel)source;
                if(targetDescriptor.getObjectType().equals(WeatherModel.class)) {
                    model = new WeatherModel();
                    model.setCity(sourceModel.getCurrent_observation().getDisplay_location().getCity());
                    model.setState(sourceModel.getCurrent_observation().getDisplay_location().getState());
                    model.setTemperature(sourceModel.getCurrent_observation().getTemp_f().toString());
                    model.setFormat(ApplicationConstants.FAHRENHEIT);
                    return model;
                }
            } catch (Exception e) {
                System.err.println(e);
                return null;
            }
        }
        return null;
    }
}
