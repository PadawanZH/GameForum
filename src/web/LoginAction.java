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
	 * @return ���ֽ��  1. Login ȱ����Ϣ�����ڵ�ǰҳ��<p>2. NotFound û�ҵ�<p>3. Succeed �ɹ���½<p>4. WrongPasswd �������<p>
	 */
	public String login(){
		/* NAME��PASSWORDΪ���򷵻ص�¼ҳ�棬������֤��¼ */
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
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "ע��ʧ�ܣ���ϵǰ�˹���Ա");
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
