
package com.expedia.weatherapp.model;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
   	private Features features;
   	private String termsofService;
   	private String version;

    public void Response(){}

 	public Features getFeatures(){
		return this.features;
	}
	public void setFeatures(Features features){
		this.features = features;
	}
 	public String getTermsofService(){
		return this.termsofService;
	}
	public void setTermsofService(String termsofService){
		this.termsofService = termsofService;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
}
