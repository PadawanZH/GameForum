package ser;

import java.util.Iterator;
import java.util.List;

import dao.Game;
import dao.GameDAO;
import dao.Guser;
import dao.GuserDAO;
import dao.Post;

public class TestService {
	private GameDAO gameDAO;
	private GuserDAO guserDAO;
	
	public void test(){
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


	public GuserDAO getGuserDAO() {
		return guserDAO;
	}


	public void setGuserDAO(GuserDAO guserDAO) {
		this.guserDAO = guserDAO;
	}
}
