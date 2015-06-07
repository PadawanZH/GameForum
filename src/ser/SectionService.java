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
 * Ϊ�����ṩ����<b>�в�֪����applicationContext�м�ƽ����userService�ܲ�����</b>
 * @author Administrator
 *
 */
public class SectionService {
	SectionDAO sectionDAO;
	GameDAO gameDAO;
	
	UserService userService;
	/**
	 * Ӧ��������ҳ���ÿ������top4����Ϸ��Section������
	 * @return
	 */
	public List<Section> getSectionByTopFourGame(){
		List<Game> top4Games = gameDAO.queryFourTopGame();
		return null;
	}
	
	/**
	 * �����Ϸר����ֻ���ǹ���Աʹ��,ע��ǰ��Ҫ�������sectionʱname����Ϊ��
	 * @param cUser
	 * @param section
	 * @return NotAdmin NameExisted Succeed
	 */
	public String addSection(Guser cUser, Section section){
		String status;
		//check admin
		if( ! userService.isAdmin(cUser.getAccount()) )
			return "NotAdmin";
		//����
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
	 * ɾ����Ϸר����ֻ���ǹ���Աʹ�ã�<b>ע����ActionҪ��section�����Խ��к���ֵ����˲�����ȷɾ��</b>
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
	 * �õ�section�Ķ�Ӧ����Ϸ,
	 * 
	 * hashset
	 * @return
	 */
	public Section GetGameInSectionByName(String gameName){
		List<Section> list = sectionDAO.findByName(gameName);
		if(list.size() != 1){
			System.out.println("SectionService.GetGameInSection() have null value");
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "Section��Game���ֲ��������������⣬����ϵ����Ա���");
			return null;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("curSection", list.get(0));
			return list.get(0);
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
