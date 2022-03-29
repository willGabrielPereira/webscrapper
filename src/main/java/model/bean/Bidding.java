package model.bean;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import connection.ConnectionFactory;

public class Bidding {
	private String title;
	private String url;
	private String status;
	private String description;
	private List<File> files;
	private List<History> historical;
	private Date date;
	
	
	public Bidding() {
		// TODO Auto-generated constructor stub
	}


	public Bidding(String title, String url, String status, String description, List<File> files, List<History> historical, Date date) {
		this.title = title;
		this.url = url;
		this.status = status;
		this.description = description;
		this.files = files;
		this.historical = historical;
		this.date = date;
	}



	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<File> getFiles() {
		return files;
	}


	public void setFiles(List<File> files) {
		this.files = files;
	}


	public List<History> getHistorical() {
		return historical;
	}


	public void setHistorical(List<History> historical) {
		this.historical = historical;
	}
	
	
	public void save() {
		ConnectionFactory cf = ConnectionFactory.init().setConnection("populate");
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
