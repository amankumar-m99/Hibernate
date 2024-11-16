package entity.e004manytomany;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private long duration;
	private int year;

	@ManyToMany
//	@JoinTable(name = "movies_join_actors" 
//		,joinColumns = {@JoinColumn(name="m_id")}, 
//		inverseJoinColumns = { @JoinColumn(name = "a_id")}
//	)
	private List<Actor> actors;

	public Movie() {
		super();
	}

	public Movie(String title, long duration, int year) {
		super();
		this.title = title;
		this.duration = duration;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}