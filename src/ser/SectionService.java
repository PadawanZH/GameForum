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
	 * Ӧ��������ҳ���ÿ������top4����Ϸ��Section������
	 * @return
	 */
	public List<Section> getSectionByTopFourGame(){
		List<Game> top4Games = gameDAO.queryFourTopGame();
		return null;
	}
	
	/**
	 * �����Ϸר����ֻ���ǹ���Աʹ��
	 * @return
	 */
	public String addSection(Guser cUser, Section section){
		return null;
	}
	/**
	 * ɾ����Ϸר����ֻ���ǹ���Աʹ��
	 * @return
	 */
	public String deleteSection(){
		return null;
	}
}
