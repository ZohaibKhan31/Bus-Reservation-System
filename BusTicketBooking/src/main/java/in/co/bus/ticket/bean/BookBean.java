package in.co.bus.ticket.bean;

import java.util.Date;

public class BookBean extends BaseBean {
	
	private long busId;
	private String busName;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String address;
	private long noOfPerson;
	private long price;
	private long finalPrice;
	private Date bookDate;
	
	
	
	
	
	
	
	
	
	

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public long getBusId() {
		return busId;
	}

	public void setBusId(long flightId) {
		this.busId = flightId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String flightName) {
		this.busName = flightName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(long noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(long finalPrice) {
		this.finalPrice = finalPrice;
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
