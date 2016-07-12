package pku.yang.model;

public class Table_config {
	
	private Integer id;
	private Integer type;
	private String type_des;
	private String db_name;
	private String db_mapping;
	private String table_name;
	private String table_mapping;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getType_des() {
		return type_des;
	}
	public void setType_des(String type_des) {
		this.type_des = type_des;
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public String getDb_mapping() {
		return db_mapping;
	}
	public void setDb_mapping(String db_mapping) {
		this.db_mapping = db_mapping;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_mapping() {
		return table_mapping;
	}
	public void setTable_mapping(String table_mapping) {
		this.table_mapping = table_mapping;
	}
}
