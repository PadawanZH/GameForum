package web;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import ser.UserService;

/**
 * 为了响应加载页面初始化的action
 * @author Administrator
 *
 */
public class AutoRequestAction {
	UserService userService;
	
	/**
	 * 处理更新cUser的请求
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
