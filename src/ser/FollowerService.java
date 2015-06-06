package ser;

import java.util.List;

import dao.Follow;
import dao.FollowDAO;
import dao.Guser;

public class FollowerService {
	FollowDAO followDAO;
	
	/**
	 * Ìí¼ÓFollow
	 * @param myAccount
	 * @param targetAccount
	 * @return
	 */
	public String followSomebody(String myAccount, String targetAccount){
		Follow follow = new Follow(new Guser(myAccount), new Guser(targetAccount));
		followDAO.save(follow);
		return "Succeed";
	}
	
	/**
	 * È¥³ýFollow
	 * @param myAccount
	 * @param targetAccount
	 * @return
	 */
	public String notFollowSombody(String myAccount, String targetAccount){
		Follow follow = new Follow(new Guser(myAccount), new Guser(targetAccount));
		List<Follow> list = followDAO.findByExample(follow);
		if(list.size() != 1){
			return "Failed";
		}else{
			followDAO.delete(list.get(0));
			return "Succeed";
		}
	}
}
