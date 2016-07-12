package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="freegroup_file")
public class FreegroupFile  {
	@Id
	@Column(name="fgfile_id")
	private String fgfile_id;
	
	@Column(name="file_id")
	private String file_id;
	
	@Column(name="folder_id")
	private String folder_id;

	public String getFgfile_id() {
		return fgfile_id;
	}

	public void setFgfile_id(String fgfile_id) {
		this.fgfile_id = fgfile_id;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}
	
	
}
