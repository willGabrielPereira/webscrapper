package model.bean;

import java.util.Date;

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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("File url=");
		builder.append(url);
		builder.append("\n date=");
		builder.append(date);
		builder.append("\n size=");
		builder.append(size);
		builder.append("\n name=");
		builder.append(name);
		builder.append("");
		return builder.toString();
	}
	
	
}
