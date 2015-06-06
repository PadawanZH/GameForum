package web;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import ser.GameInfoService;
import ser.UserService;

/**
 * Ϊ����Ӧ����ҳ���ʼ����action
 * @author Administrator
 *
 */
public class AutoRequestAction {
	UserService userService;
	GameInfoService gameInfoService;
	
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
	
	/**
	 * 
	 * @return
	 */
	public String updateTop4GameInfo(){
		gameInfoService.grubFourTopGames();
		return "Succeed";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GameInfoService getGameInfoService() {
		return gameInfoService;
	}

	public void setGameInfoService(GameInfoService gameInfoService) {
		this.gameInfoService = gameInfoService;
	}
}
