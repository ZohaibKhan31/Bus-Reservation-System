package in.co.bus.ticket.bean;

import java.util.Date;

public class BusBean extends BaseBean {
	
	
	private String busNo;
	private String busName;
	private String fromCity;
	private String toCity;
	private Date date;
	private String description;
	private String time;
	private String travelDuraion;
	private long ticketPrice;
	private String busStopName;
	
	

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTravelDuraion() {
		return travelDuraion;
	}

	public void setTravelDuraion(String travelDuraion) {
		this.travelDuraion = travelDuraion;
	}

	public long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getBusStopName() {
		return busStopName;
	}

	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
