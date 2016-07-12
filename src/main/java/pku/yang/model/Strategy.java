package pku.yang.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * 访问策略实体类
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */

public class Strategy {
	
	private Integer strategyID;			//访问策略ID
	private String 	propertyExpression;	//属性表达式
	private Integer allowCreateFloder;	//允许创建目录
	private Integer allowRenameFloder;	//允许共享目录
	private Integer allowDeleteFloder;	//允许删除目录
	private Integer allowUploadFile;	//允许上传文件
	private Integer allowDownloadFile;	//允许下载文件
	private Integer allowDeleteFile;	//允许删除文件
	private Integer operateWays;		//文件操作方式：0表示普通方式；1表示ABE方式
	private Integer integrity;			//是否支持完整性
	private Set<AccessControl> accessControls = new HashSet<AccessControl>();//策略ID
	
	@Column(name="strategy_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Integer getStrategyID() {
		return strategyID;
	}
	public void setStrategyID(Integer strategyID) {
		this.strategyID = strategyID;
	}
	
	@Column(name="property_expression")
	public String getPropertyExpression() {
		return propertyExpression;
	}
	public void setPropertyExpression(String propertyExpression) {
		this.propertyExpression = propertyExpression;
	}
	
	@Column(name="allow_create_floder")
	public Integer getAllowCreateFloder() {
		return allowCreateFloder;
	}
	public void setAllowCreateFloder(Integer allowCreateFloder) {
		this.allowCreateFloder = allowCreateFloder;
	}
	
	@Column(name="allow_rename_floder")
	public Integer getAllowRenameFloder() {
		return allowRenameFloder;
	}
	public void setAllowRenameFloder(Integer allowRenameFloder) {
		this.allowRenameFloder = allowRenameFloder;
	}
	
	@Column(name="allow_delete_floder")
	public Integer getAllowDeleteFloder() {
		return allowDeleteFloder;
	}
	public void setAllowDeleteFloder(Integer allowDeleteFloder) {
		this.allowDeleteFloder = allowDeleteFloder;
	}
	
	@Column(name="allow_upload_file")
	public Integer getAllowUploadFile() {
		return allowUploadFile;
	}
	public void setAllowUploadFile(Integer allowUploadFile) {
		this.allowUploadFile = allowUploadFile;
	}
	
	@Column(name="allow_download_file")
	public Integer getAllowDownloadFile() {
		return allowDownloadFile;
	}
	public void setAllowDownloadFile(Integer allowDownloadFile) {
		this.allowDownloadFile = allowDownloadFile;
	}
	
	@Column(name="allow_delete_file")
	public Integer getAllowDeleteFile() {
		return allowDeleteFile;
	}
	public void setAllowDeleteFile(Integer dAllowDeleteFile) {
		this.allowDeleteFile = dAllowDeleteFile;
	}
	@Column(name="operate_ways")
	public Integer getOperateWays() {
		return operateWays;
	}
	public void setOperateWays(Integer operateWays) {
		this.operateWays = operateWays;
	}
	
	public Integer getIntegrity() {
		return integrity;
	}
	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}
	
	@ManyToMany(mappedBy="strategys",fetch=FetchType.EAGER)
	public Set<AccessControl> getAccessControls() {
		return accessControls;
	}
	public void setAccessControls(Set<AccessControl> accessControls) {
		this.accessControls = accessControls;
	}
	
}
