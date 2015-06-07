package web;

import ser.SectionService;

public class GameSectionAction {
	SectionService sectionService;
	String gameName;
	Integer gameID;
	
	public String getSectionOfGamePage(){
		if(sectionService.GetGameInSectionByName(gameName) == true){
			return "Succeed";
		}else{
			return "Failed";
		}
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
}
