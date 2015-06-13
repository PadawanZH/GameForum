package web;

import org.apache.struts2.ServletActionContext;

import ser.GameInfoService;

public class GameInfoAction {

	GameInfoService gameInfoService;
	
	/**
	 * ��service����ɽ��б�浽session�е�����
	 * @return
	 */
	public String getMoreGameByType(){
		String gameType = ServletActionContext.getRequest().getParameter("gameType");
		return gameInfoService.findGamesByType(gameType);
	}

	public GameInfoService getGameInfoService() {
		return gameInfoService;
	}

	public void setGameInfoService(GameInfoService gameInfoService) {
		this.gameInfoService = gameInfoService;
	}
}
