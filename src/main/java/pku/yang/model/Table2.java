package pku.yang.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 访问策略实体类
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */

@Entity(name="table2")
public class Table2 {
	
	private Integer table2id;			
	private String 	column1;	
	private String 	column2;	
	private String 	column3;	
	private String 	column4;	
	private String 	column5;	
	private String 	column6;	
	private String 	column7;	
	private String 	column8;	
	private String 	column9;	
	private String 	column10;	
	private String 	column11;	
	private String 	column12;	
	private Table1 table1;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Integer getTable2id() {
		return table2id;
	}
	public void setTable2id(Integer table2id) {
		this.table2id = table2id;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
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
	public String getColumn5() {
		return column5;
	}
	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	public String getColumn6() {
		return column6;
	}
	public void setColumn6(String column6) {
		this.column6 = column6;
	}
	public String getColumn7() {
		return column7;
	}
	public void setColumn7(String column7) {
		this.column7 = column7;
	}
	public String getColumn8() {
		return column8;
	}
	public void setColumn8(String column8) {
		this.column8 = column8;
	}
	public String getColumn9() {
		return column9;
	}
	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	public String getColumn10() {
		return column10;
	}
	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	public String getColumn11() {
		return column11;
	}
	public void setColumn11(String column11) {
		this.column11 = column11;
	}
	public String getColumn12() {
		return column12;
	}
	public void setColumn12(String column12) {
		this.column12 = column12;
	}
	
	@OneToOne(mappedBy="table2",fetch=FetchType.EAGER) 
	public Table1 getTable1() {
		return table1;
	}
	public void setTable1(Table1 table1) {
		this.table1 = table1;
	}
	
}
