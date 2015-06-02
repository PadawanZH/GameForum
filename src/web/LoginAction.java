package web;

import ser.UserService;

public class LoginAction {
	private UserService userService;
	
	/**
	 * 
	 * @param account
	 * @param passwd
	 * @return ���ֽ��  1. Login ȱ����Ϣ�����ڵ�ǰҳ��<p>2. NotFound û�ҵ�<p>3. Succeed �ɹ���½<p>4. WrongPasswd �������<p>
	 */
	public String login(String account, String passwd){
		/* NAME��PASSWORDΪ���򷵻ص�¼ҳ�棬������֤��¼ */
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
