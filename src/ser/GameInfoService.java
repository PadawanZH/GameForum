package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;

public class GameInfoService {
	GameDAO gameDAO;
	
	/**
	 * ���ظ����������������ĸ���Ϸ,��������ʹ��
	 * @return
	 */
	public List<Game> grubFourTopGames(){
		return gameDAO.queryFourTopGame();
	}
}
