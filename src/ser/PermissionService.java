package ser;

import dao.Guser;
import dao.GuserDAO;
import dao.UsergroupDAO;

/**
 * ����鿴�����Ƿ����Ȩ�ޣ���÷���ʧ�ܺ�Ӧ�е��û���<p>
 * <b><font color="red">��δʵ��</font></b>
 * @author Administrator
 *
 */
public class PermissionService {
	
	GuserDAO guserDAO;
	UsergroupDAO usergroupDAO;
	
	public String checkPermission(Guser guser,String action){
		return null;
	}

	/**
	 * @return the guserDAO
	 */
	public GuserDAO getGuserDAO() {
		return guserDAO;
	}

	/**
	 * @param guserDAO the guserDAO to set
	 */
	public void setGuserDAO(GuserDAO guserDAO) {
		this.guserDAO = guserDAO;
	}

	/**
	 * @return the usergroupDAO
	 */
	public UsergroupDAO getUsergroupDAO() {
		return usergroupDAO;
	}

	/**
	 * @param usergroupDAO the usergroupDAO to set
	 */
	public void setUsergroupDAO(UsergroupDAO usergroupDAO) {
		this.usergroupDAO = usergroupDAO;
	}
}
