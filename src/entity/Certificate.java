package entity;

import javax.persistence.Embeddable;

@Embeddable
public class Certificate {
	private String title;
	private int durationMonths;
	public Certificate() {
		super();
	}
	public Certificate(String title, int durationMonths) {
		super();
		this.title = title;
		this.durationMonths = durationMonths;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDurationMonths() {
		return durationMonths;
	}
	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}
	@Override
	public String toString() {
		return "Certificate [title=" + title + ", durationMonths=" + durationMonths + "]";
	}
}
