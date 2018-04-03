package mesage;

import javax.persistence.*;

import email.Email;

@Entity(name = "message2")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column
	String content;

	@OneToOne
	Email email;

	public Message() {
	}

	public Message(String content) {
		setContent(content);
	}

	private void setContent(String content2) {
		this.content = content2;

	}

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}
}
