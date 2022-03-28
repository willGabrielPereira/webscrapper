package model.bean;

import java.util.Date;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("History [date=");
		builder.append(date);
		builder.append(", status=");
		builder.append(status);
		builder.append(", reason=");
		builder.append(reason);
		builder.append("]");
		return builder.toString();
	}
	
	
}
