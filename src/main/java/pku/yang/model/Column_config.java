package pku.yang.model;

public class Column_config {
	
	private Integer cid;
	private String table_name;
	private String column_name;
	private String column_mapping;
	private Integer is_attribute;
	private String is_valid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getColumn_mapping() {
		return column_mapping;
	}
	public void setColumn_mapping(String column_mapping) {
		this.column_mapping = column_mapping;
	}
	public Integer getIs_attribute() {
		return is_attribute;
	}
	public void setIs_attribute(Integer is_attribute) {
		this.is_attribute = is_attribute;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
}
