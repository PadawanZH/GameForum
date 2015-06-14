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
	 * 返回各类中贴子数最大的四个游戏,在主界面使用
	 * @return
	 */
	public boolean grubFourTopGames(){
		List<Game> list = gameDAO.queryFourTopGame();
		ServletActionContext.getRequest().getSession().setAttribute("top4GameList", list);
		return true;
		
	}
	/**
	 * 根据类型查找游戏，将list结果存到session中名为gamesOfType的变量
	 * @param type
	 * @return
	 */
	public String findGamesByType(String type){
		List<Game> list = gameDAO.findByType(type);
		if(list.size() ==  0){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "该类型没有游戏，请联系管理员");
			return "Failed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("gamesOfType", list);
			return "Succeed";
		}
	}
	
	public String delGame(Integer delGameID){
		Game game = gameDAO.findById(delGameID);
		if(game == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "无此游戏，请刷新");
			return "Failed";
		}else{
			gameDAO.delete(game);
			return "Succeed";
		}
		
	}
	
	/**
	 * 添加游戏，需要上一级验证管理员
	 * @param admin
	 * @param game
	 * @return
	 */
	public String addGame(Game game){
		//可能还要一定的初始化
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
