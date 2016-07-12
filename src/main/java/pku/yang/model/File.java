package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="File")
public class File {
	@Id
	@Column(name="file_id")
	private String file_id;
	
	

	@Column(name="file_name")
	private String file_name;
	
	@Column(name="folderId")
	private String folderId;
	
	@Column(name="exp_name")
	private String exp_name;
	
	@Column(name="owner")
	private String owner;
	
	@Column(name="upload_time")
	private String upload_time;
	
	@Column(name="cloud_path")
	private String cloud_path;
	
	@Column(name="share_type")
	private String shareType;
	
	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getIntegrityType() {
		return integrityType;
	}

	public void setIntegrityType(String integrityType) {
		this.integrityType = integrityType;
	}

	@Column(name="integrity_type")
	private String integrityType; 
	
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getExp_name() {
		return exp_name;
	}

	public void setExp_name(String exp_name) {
		this.exp_name = exp_name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}

	public String getCloud_path() {
		return cloud_path;
	}

	public void setCloud_path(String cloud_path) {
		this.cloud_path = cloud_path;
	}
}
