package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;

public class TestService {
	private GameDAO gameDAO;
	
	public void test(){
		List<Game> list = gameDAO.queryFourTopGame();
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
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
