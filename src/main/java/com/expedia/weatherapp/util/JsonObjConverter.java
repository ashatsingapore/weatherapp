package com.expedia.weatherapp.util;

import com.expedia.weatherapp.model.WunderGroundModel;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This utility class is for JSON conversion
 */
public class JsonObjConverter implements GenericConverter {

//    private static final Logger logger = LoggerFactory.getLogger(JsonObjConverter.class);

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairs = new HashSet<ConvertiblePair>();

        pairs.add(new ConvertiblePair(String.class, WunderGroundModel.class));

        return pairs;
    }

    /**
     * Convert JSON to Java Object
     *
     * @param source - Source Object
     * @param sourceDescriptor - Source Type
     * @param targetDescriptor - Target Type
     * @return
     */
    @Override
    public Object convert(Object source, TypeDescriptor sourceDescriptor,
                          TypeDescriptor targetDescriptor) {

        if(source instanceof String)  {
            ObjectMapper mapper = new ObjectMapper();
            WunderGroundModel model = null;
            try {
                // Convert JSON Object to Generic Type
                if(targetDescriptor.getObjectType().equals(WunderGroundModel.class)) {
                    if(((String) source).indexOf("null") == 0 ){
                        source = ((String) source).substring(((String) source).indexOf("null") + 4);
                    }
                    mapper.configure(
                            DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    mapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
                    model =  mapper.readValue(source.toString(), WunderGroundModel.class);

                    return model;
                }
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
