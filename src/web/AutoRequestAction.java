package web;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import ser.UserService;

/**
 * Ϊ����Ӧ����ҳ���ʼ����action
 * @author Administrator
 *
 */
public class AutoRequestAction {
	UserService userService;
	
	/**
	 * �������cUser������
	 * @return
	 */
	public String updateCUser(){
		Guser cUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("cUser");
		if(cUser != null && cUser.getAccount() != null){
			userService.RequestCurUserDataWithAccount(cUser.getAccount());
			return "Succeed";
		}else{
			return "Failed";
		}
	}
}
