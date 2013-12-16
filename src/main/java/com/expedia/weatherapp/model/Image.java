
package com.expedia.weatherapp.model;

import java.io.Serializable;
import java.util.List;

public class Image implements Serializable {
   	private String link;
   	private String title;
   	private String url;

    public void Image(){}

 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
