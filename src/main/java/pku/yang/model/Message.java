package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="message")
public class Message {
	@Id
	@Column(name="mess_id")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	private String mess_id;
	
	public String getMess_id() {
		return mess_id;
	}

	public void setMess_id(String mess_id) {
		this.mess_id = mess_id;
	}

	@Column(name="user_id")
	private String user_id;
	
	@Column(name="fg_id")
	private String fg_id;
	
	@Column(name="state")
	private String state;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFg_id() {
		return fg_id;
	}

	public void setFg_id(String fg_id) {
		this.fg_id = fg_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}
