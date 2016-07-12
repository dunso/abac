package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="folder")
public class Folder {
	@Id
	@Column(name="folder_id")
	private String folderID;
	
	@Column(name="folder_name")
	private String name;
	
	@Column(name="father_id")
	private String fatherID;
	
	@Column(name="storage_id")
	private String storageID;
	
	@Column(name="storage_type")
	private String storageType;
	
	@Column(name="whether_root")
	private String whetherRoot;
	
	@Column(name="creater")
	private String creater;
	
	@Column(name="create_date")
	private String createDate;
	
	@Column(name="share_type")
	private String shareType;
	
	@Column(name="integrity_type")
	private String integrityType; //共享方式：0（普通方式共享）；1（ABE方式共享              完整性支持：0（不支持完整性）；1（支持完整性）

	public String getFolderID() {
		return folderID;
	}

	public void setFolderID(String folderID) {
		this.folderID = folderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherID() {
		return fatherID;
	}

	public void setFatherID(String fatherID) {
		this.fatherID = fatherID;
	}

	public String getStorageID() {
		return storageID;
	}

	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getWhetherRoot() {
		return whetherRoot;
	}

	public void setWhetherRoot(String whetherRoot) {
		this.whetherRoot = whetherRoot;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

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
	




}
