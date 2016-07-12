package pku.yang.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class Token {
	
	@Id
	@Column(name="token_id")
	private String token_id;
	
	@Column(name="deadLine")
	private String deadLine;
	
	public void setTokenId(String token_id){
		this.token_id = token_id;
	}
	
	public String getTokenId(){
		return this.token_id;
	}
	
	public void setDeadLine(String deadLine){
		this.deadLine = deadLine;
	}
	
	public String getDeadLine(){
		return this.deadLine;
	}

}
