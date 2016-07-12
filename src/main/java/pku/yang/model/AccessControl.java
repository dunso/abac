package pku.yang.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


/**
 * 访问控制实体类
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */

public class AccessControl {
	

	private Integer accessContorlId;	//访问控制ID
	private Integer groupId;			//组ID
	private String  path;				//文件/目录ID(原打算存完整路劲的，后来因为项目模块整合，改为了ID，但名字仍然保留未修改，修改涉及的面太大)
	private Set<Strategy> strategys = new HashSet<Strategy>();	//访问策略（这是外键，和策略表关联）
	
	@Column(name="access_control_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Integer getAccessContorlId() {
		return accessContorlId;
	}

	public void setAccessContorlId(Integer accessContorlId) {
		this.accessContorlId = accessContorlId;
	}

	@Column(name="group_id")
	//@ManyToOne(fetch=FetchType.LAZY)
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@JoinTable(name="access_control_strategy",
			joinColumns={@JoinColumn(name="access_control_id",referencedColumnName="access_control_id")},
			inverseJoinColumns={@JoinColumn(name="strategy_id",referencedColumnName="strategy_id")})
	@ManyToMany
	public Set<Strategy> getStrategys() {
		return strategys;
	}

	public void setStrategys(Set<Strategy> strategys) {
		this.strategys = strategys;
	}
}
