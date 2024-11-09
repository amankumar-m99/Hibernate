package entity.e003onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Capital {

	@Id
	private long id;

	private String name;
	private int pincode;

	@OneToOne//(mappedBy = "capital")
	private Country country;

	public Capital() {
		super();
	}

	public Capital(long id, String name, int pincode) {
		super();
		this.id = id;
		this.name = name;
		this.pincode = pincode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
