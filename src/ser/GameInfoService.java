package ser;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Game;
import dao.GameDAO;
import dao.Guser;
import dao.Post;
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
	 * �������Ͳ�����Ϸ����list����浽session����ΪgamesOfType�ı���
	 * @param type
	 * @return
	 */
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
	
	public String delGame(Integer delGameID){
		Game game = gameDAO.findById(delGameID);
		if(game == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�޴���Ϸ����ˢ��");
			return "Failed";
		}else{
			gameDAO.delete(game);
			return "Succeed";
		}
		
	}
	
	/**
	 * �����Ϸ����Ҫ��һ����֤����Ա
	 * @param admin
	 * @param game
	 * @return
	 */
	public String addGame(Game game){
		//���ܻ�Ҫһ���ĳ�ʼ��
		gameDAO.save(game);
		return "Succeed";
	}
	
	public String findAllGame(){
		List<Game> list = gameDAO.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("allGameList", list);
		System.out.println("GameInfoService.findAllGame() and list size is : "+ list.size());
		return "Succeed";
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
