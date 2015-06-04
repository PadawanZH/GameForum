package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * 在进入游戏专区需要填写游戏信息时使用，可能要对Name加一个unique的限制
	 * @return
	 */
	public Game getGameByName(){
		return null;
	}
	
	/**
	 * 返回各类中贴子数最大的四个游戏,在主界面使用
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
