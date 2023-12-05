package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="student_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int addressId;

	@Column(length = 30)
	private String street;

	@Column(length = 30)
	private String city;

	private int pincode;

	@Column(name="added_date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Transient
	private int ignoredValue;

//	@Lob
//	private byte[] image;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int addressId, String street, String city, int pincode, Date date) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.date = date;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", houseNumber=" + street + ", city=" + city + ", pincode="
				+ pincode + ", date=" + date + "]";
	}
}
