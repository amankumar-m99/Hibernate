package entity.address;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String city;
	private String state;
	private String landmark;
	private int pincode;
	public Address() {
		super();
	}
	public Address(String city, String state, String landmark, int pincode) {
		super();
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
