package pku.yang.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 访问控制实体类
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */

@Entity(name="table1")
public class Table1{

	private Integer table1id;
	private Integer column1;  
	private String  column2;
	private String  column3;
	private String  column4;
	private Table2 table2;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Integer getTable1id() {
		return table1id;
	}

	public void setTable1id(Integer table1id) {
		this.table1id = table1id;
	}

	public Integer getColumn1() {
		return column1;
	}

	public void setColumn1(Integer column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn4() {
		return column4;
	}
	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	@OneToOne  
    @JoinColumn(name="table2id",insertable=true,unique=true)  
	public Table2 getTable2() {
		return table2;
	}

	public void setTable2(Table2 table2) {
		this.table2 = table2;
	}

	
}
