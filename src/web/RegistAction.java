package web;

import dao.Admin;
import dao.Guser;
import ser.UserService;

public class RegistAction {
	private UserService userService;
	private Guser guser;
	
	public String regist(){
		return userService.userRegist(guser);
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
	 * @return the guser
	 */
	public Guser getGuser() {
		return guser;
	}

	/**
	 * @param guser the guser to set
	 */
	public void setGuser(Guser guser) {
		this.guser = guser;
	}
	
}
