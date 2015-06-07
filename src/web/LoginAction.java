package web;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import ser.UserService;

public class LoginAction {
	private UserService userService;
	private String account,passwd;
	private Guser curUser;
	/**
	 * 
	 * @param account
	 * @param passwd
	 * @return 四种结果  1. Login 缺少信息保留在当前页面<p>2. NotFound 没找到<p>3. Succeed 成功登陆<p>4. WrongPasswd 密码错误<p>
	 */
	public String login(){
		/* NAME和PASSWORD为空则返回登录页面，否则验证登录 */
		if(account == null || passwd == null){
			System.out.println("Login");
			return "Login";
		}else{
			String status = userService.login(account, passwd);
			System.out.println("LoginAction.login() " + status);
			return status;
		}
	}
	
	public String logoff(){
		Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
		String status = "";
		if(cUser != null){
			userService.logoff(cUser.getAccount());
			status = "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "注销失败，联系前端管理员");
			status = "Failed";
		}
		return status;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Guser getCurUser() {
		return curUser;
	}

	public void setCurUser(Guser curUser) {
		this.curUser = curUser;
	}
}
