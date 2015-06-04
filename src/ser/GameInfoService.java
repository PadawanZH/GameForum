package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * 返回各类中贴子数最大的四个游戏,在主界面使用
	 * @return
	 */
	public List<Game> grubFourTopGames(){
		return gameDAO.queryFourTopGame();
	}
}
