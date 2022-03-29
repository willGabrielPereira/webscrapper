package model.bean;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import connection.ConnectionFactory;

public class History {

	private Date date;
	private String status;
	private String reason;
	
	public History(Date date, String status, String reason) {
		this.date = date;
		this.status = status;
		this.reason = reason;
	}
	
	public History() {}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public void save() {
		ConnectionFactory cf = ConnectionFactory.init().setConnection("histories");
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
