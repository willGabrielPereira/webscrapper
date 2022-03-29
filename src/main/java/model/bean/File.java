package model.bean;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import connection.ConnectionFactory;

public class File {
	private String url;
	private Date date;
	private String size;
	private String name;
	
	
	public File() {
		// TODO Auto-generated constructor stub
	}


	public File(String url, Date date, String size, String name) {
		this.url = url;
		this.date = date;
		this.size = size;
		this.name = name;
	}


	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}



	public void save() {
		ConnectionFactory cf = ConnectionFactory.init().setConnection("files");
		try {
			cf.post(this.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(this);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	
}
