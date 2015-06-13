package web;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import dao.Post;
import dao.Section;
import ser.PostService;
import ser.SectionService;

public class GameSectionAction {
	SectionService sectionService;
	PostService postService;
	String gameName;
	Integer gameID;
	String title;
	String contents;
	
	/**
	 * gameSectionAction���÷���������curSection
	 * @return
	 */
	public String getSectionOfGamePage(){
		Section section = sectionService.GetGameInSectionByName(gameName);
		if(section != null){
			postService.getPostForSectionOrderByPostTime(section);
			return "Succeed";
		}else{
			return "Failed";
		}
	}
	
	/**
	 * postDetail ���� �б�ʱ�ã�����session�е�list
	 * @return
	 */
	public String getBackToSection(){
		Section section = (Section) ServletActionContext.getRequest().getSession().getAttribute("curSection");
		if(section != null){
			postService.getPostForSectionOrderByPostTime(section);
			return "Succeed";
		}else{
			return "Failed";
		}
	}
	
	/**
	 * ����������ˢ��PostListOfSection
	 * @return
	 */
	public String sendPostAction(){
		//���session��section��guser
		Section sectionOfPost = (Section) ServletActionContext.getRequest().getSession().getAttribute("curSection");
		Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
		//����
		String status = postService.sendPostInSection(title,contents,sectionOfPost,cUser);
		//ˢ��post�б�
		postService.getPostForSectionOrderByPostTime(sectionOfPost);
		return status;
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getGameID() {
		return gameID;
	}

	public void setGameID(Integer gameID) {
		this.gameID = gameID;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
