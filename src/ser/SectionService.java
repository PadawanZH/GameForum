package ser;

import java.util.List;

import dao.Game;
import dao.GameDAO;
import dao.Guser;
import dao.Section;
import dao.SectionDAO;

public class SectionService {
	SectionDAO sectionDAO;
	GameDAO gameDAO;
	/**
	 * 应当是在主页面的每个类别的top4的游戏的Section的需求
	 * @return
	 */
	public List<Section> getSectionByTopFourGame(){
		List<Game> top4Games = gameDAO.queryFourTopGame();
		return null;
	}
	
	/**
	 * 添加游戏专区，只能是管理员使用
	 * @return
	 */
	public String addSection(Guser cUser, Section section){
		return null;
	}
	/**
	 * 删除游戏专区，只能是管理员使用
	 * @return
	 */
	public String deleteSection(){
		return null;
	}
}
