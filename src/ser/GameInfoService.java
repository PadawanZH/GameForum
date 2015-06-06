package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;
import dao.Section;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * ���ظ����������������ĸ���Ϸ,��������ʹ��
	 * @return
	 */
	public List<Game> grubFourTopGames(){
		return gameDAO.queryFourTopGame();
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
