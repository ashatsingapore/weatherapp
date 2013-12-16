
package com.expedia.weatherapp.model;

import java.io.Serializable;
import java.util.List;

public class Features implements Serializable {
   	private Number conditions;

    public void Features(){}

 	public Number getConditions(){
		return this.conditions;
	}
	public void setConditions(Number conditions){
		this.conditions = conditions;
	}
}
