package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * �ڽ�����Ϸר����Ҫ��д��Ϸ��Ϣʱʹ�ã�����Ҫ��Name��һ��unique������
	 * @return
	 */
	public Game getGameByName(){
		return null;
	}
	
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
