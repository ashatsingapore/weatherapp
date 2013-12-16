
package com.expedia.weatherapp.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WunderGroundModel implements Serializable {
   	private Current_observation current_observation;
   	private Response response;

    public void WunderGroundModel() {}

 	public Current_observation getCurrent_observation(){
		return this.current_observation;
	}
	public void setCurrent_observation(Current_observation current_observation){
		this.current_observation = current_observation;
	}
 	public Response getResponse(){
		return this.response;
	}
	public void setResponse(Response response){
		this.response = response;
	}
}
