package pku.yang.model;

/**
 * user role
 * @author summer
 *
 */
public class Role {
	/** system manager 1XX*/
	public boolean isSysMng;
	
	/**group manager X1X*/
	public boolean isGroupMng;
	
	/**common user XX1*/
	public boolean isCommonUser;
	
	public Role(int role){
		isSysMng = (role & 4) != 0;
		isGroupMng = (role & 2) != 0;
		isCommonUser = (role & 1) != 0;
	}
}
