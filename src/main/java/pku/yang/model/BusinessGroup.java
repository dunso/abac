package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="businessgroup")


public class BusinessGroup {

	@Id
	@Column(name="id")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="admin_id")
	private String adminId;
	
	@Column(name="storage_id",length=128)
	private String storageId;
	
	
	@Column(name="admin_attrs")
	private String adminAttrs;
	
	@Column(name="u_attrs")
	private String uAttrs;
	
	@Column(name="uids")
	private String uids;
	
	@Column(name="ctime")
	private String ctime;
	
	@Column(name="utime")
	private String utime;
	
	public String getCtime(){
		return this.ctime;
	}
	
	public void setCtime(String ctime){
		this.ctime = ctime;
	}
	
	public String getUtime(){
		return this.utime;
	}
	
	public void setUptime(String utime){
		this.utime = utime;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getStorageId(){
		return storageId;
	}
	
	public void setStorageId(String storageId){
		this.storageId = storageId;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String ID){
		this.id = ID;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public String getAdminAttrs() {
		return adminAttrs;
	}

	public void setAdminAttrs(String adminAttrs) {
		this.adminAttrs = adminAttrs;
	}

	public String getUAttrs() {
		return uAttrs ;
	}
	
	public void setUAttrs(String uAttrs) {
		this.uAttrs = uAttrs ;
	}

}
