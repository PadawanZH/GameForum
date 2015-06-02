package web;

import ser.UserService;

public class LoginAction {
	private UserService userService;
	
	/**
	 * 
	 * @param account
	 * @param passwd
	 * @return 四种结果  1. Login 缺少信息保留在当前页面<p>2. NotFound 没找到<p>3. Succeed 成功登陆<p>4. WrongPasswd 密码错误<p>
	 */
	public String login(String account, String passwd){
		/* NAME和PASSWORD为空则返回登录页面，否则验证登录 */
		if(account == null || passwd == null){
			return "Login";
		}else{
			return userService.login(account, passwd);
		}
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
}
