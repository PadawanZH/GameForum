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
