package ser;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Game;
import dao.GameDAO;
import dao.Section;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * ���ظ����������������ĸ���Ϸ,��������ʹ��
	 * @return
	 */
	public boolean grubFourTopGames(){
		List<Game> list = gameDAO.queryFourTopGame();
		ServletActionContext.getRequest().getSession().setAttribute("top4GameList", list);
		return true;
		
	}
	
	public String findGamesByType(String type){
		List<Game> list = gameDAO.findByType(type);
		if(list.size() ==  0){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "������û����Ϸ������ϵ����Ա");
			return "Failed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("gamesOfType", list);
			return "Succeed";
		}
	}

	/**
	 * @return the gameDAO
	 */
	public GameDAO getGameDAO() {
		return gameDAO;
	}

	/**
	 * @param gameDAO the gameDAO to set
	 */
	public void setGameDAO(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
}
