package ser;

import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import dao.Game;
import dao.GameDAO;
import dao.Guser;
import dao.Section;
import dao.SectionDAO;

/**
 * 为分区提供服务，<b>尚不知道在applicationContext中加平级的userService能不能用</b>
 * @author Administrator
 *
 */
public class SectionService {
	SectionDAO sectionDAO;
	GameDAO gameDAO;
	
	UserService userService;
	/**
	 * 应当是在主页面的每个类别的top4的游戏的Section的需求
	 * @return
	 */
	public List<Section> getSectionByTopFourGame(){
		List<Game> top4Games = gameDAO.queryFourTopGame();
		return null;
	}
	
	/**
	 * 添加游戏专区，只能是管理员使用,注意前端要设置添加section时name不能为空
	 * @param cUser
	 * @param section
	 * @return NotAdmin NameExisted Succeed
	 */
	public String addSection(Guser cUser, Section section){
		String status;
		//check admin
		if( ! userService.isAdmin(cUser.getAccount()) )
			return "NotAdmin";
		//查重
		List<Section> list = sectionDAO.findByName(section.getName());
		if(list.size() != 0){
			status = "NameExisted";
		}else{
			sectionDAO.save(section);
			status = "Succeed";
		}
		return status;
		
	}
	/**
	 * 删除游戏专区，只能是管理员使用，<b>注意在Action要对section的属性进行合理赋值，如此才能正确删除</b>
	 * @return
	 */
	public String deleteSection(Guser cUser, Section section){

		List<Section> list = sectionDAO.findByExample(section);
		
		if(list.size() != 1){
			return "Failed";
		}else{
			sectionDAO.delete(list.get(0));
			return "Succeed";
		}
	}
	/**
	 * 得到section的对应的游戏,
	 * 
	 * hashset
	 * @return
	 */
	public boolean GetGameInSectionByName(String gameName){
		System.out.println(gameName);
		List<Section> list = sectionDAO.findByName(gameName);
		if(list.size() != 1){
			System.out.println("SectionService.GetGameInSection() have null value");
			return false;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("curSection", list.get(0));
			return true;
		}
	}

	/**
	 * @return the sectionDAO
	 */
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}

	/**
	 * @param sectionDAO the sectionDAO to set
	 */
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
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

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
