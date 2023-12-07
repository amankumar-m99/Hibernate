package entity.onetomany;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@Column(name="book_id")
	private int id;
	private String title;
	@ManyToOne
	private Author author;
	private Date dateOfPublish;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String title, Author author, Date dateOfPublish) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.dateOfPublish = dateOfPublish;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Date getDateOfPublish() {
		return dateOfPublish;
	}
	public void setDateOfPublish(Date dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", dateOfPublish=" + dateOfPublish + "]";
	}
}
