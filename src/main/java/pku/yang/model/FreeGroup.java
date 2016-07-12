package pku.yang.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="free_group")
public class FreeGroup {
	@Id
	@Column(name="fg_id")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	private String fg_id;
	
	@Column(name="fg_name")
	private String fg_name;
	
	@Column(name="fg_manager")
	private String fg_manager;
	
	@Column(name="fg_userlist")
	private String fg_userlist;

	@Column(name="storgeid")
	private String storgeid;
	
	
	
	public String getFg_id() {
		return fg_id;
	}

	public void setFg_id(String fg_id) {
		this.fg_id = fg_id;
	}

	public String getFg_name() {
		return fg_name;
	}

	public void setFg_name(String fg_name) {
		this.fg_name = fg_name;
	}

	public String getFg_manager() {
		return fg_manager;
	}

	public void setFg_manager(String fg_manager) {
		this.fg_manager = fg_manager;
	}

	public String getFg_userlist() {
		return fg_userlist;
	}

	public void setFg_userlist(String fg_userlist) {
		this.fg_userlist = fg_userlist;
	}

	public String getStorgeid() {
		return storgeid;
	}

	public void setStorgeid(String storgeid) {
		this.storgeid = storgeid;
	}



	
}
