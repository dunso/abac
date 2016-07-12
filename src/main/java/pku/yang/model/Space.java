package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="space")
public class Space {
	@Id
	@Column(name="id")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	private String spaceID;
	
	@Column(name="name")
	private String spaceName;
	
	@Column(name="size")
	private int spaceSize;//KB 
	
	@Column(name="root_directory")
	private String rootDirectory;
	
 
	public String getID() {
		return spaceID;
	}

	public void setID(String id) {
		this.spaceID = id;
	}

	public String getName() {
		return spaceName;
	}

	public void setName(String name) {
		this.spaceName = name;
	}

	public int getSize() {
		return spaceSize;
	}

	public void setSize(int size) {
		this.spaceSize = size;
	}

	public String getRoot() {
		return rootDirectory;
	}

	public void setRoot(String directory) {
		this.rootDirectory = directory;
	}
 
 
	




}
