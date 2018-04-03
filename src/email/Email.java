package email;
import javax.persistence.*;

import mesage.Message;
@Entity(name = "Email2")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String subject;
	@OneToOne(mappedBy = "email")
	Message message;

	public Email() {
	}

	public Email(String subject) {
		setSubject(subject);
	}
	public void setSubject(String subject2){
		subject=subject2;
		
	}

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}
}