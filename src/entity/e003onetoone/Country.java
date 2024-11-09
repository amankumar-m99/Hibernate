package entity.e003onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Country {

	@Id
	private long id;

	@Column(name="Namee", length=33, nullable = false)
	private String name;
	private String abbrevation;
	private int countryCode;

	@OneToOne//(mappedBy = "country")
	private Capital capital;

	public Country() {
		super();
	}

	public Country(long id, String name, String abbrevation, int countryCode) {
		super();
		this.id = id;
		this.name = name;
		this.abbrevation = abbrevation;
		this.countryCode = countryCode;
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

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public Capital getCapital() {
		return capital;
	}

	public void setCapital(Capital capital) {
		this.capital = capital;
	}

}
