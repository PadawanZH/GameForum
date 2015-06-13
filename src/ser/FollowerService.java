package ser;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Follow;
import dao.FollowDAO;
import dao.Guser;

public class FollowerService {
	FollowDAO followDAO;
	
	/**
	 * 添加Follow,由cUser调用，follow rUser
	 * @param myAccount
	 * @param targetAccount
	 * @return
	 */
	public String followSomebody(Guser cUser, Guser rUser){
		if(cUser == null || rUser == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "关注失败，请登录");
			return "Failed";
		}
		if( cUser.getAccount().equals(rUser.getAccount())){//follow的是自己
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "关注失败，您关注的是自己");
			return "Failed";
		}else{
			Follow follow = new Follow(cUser, rUser);
			
			if(cUser.getFollowsForFromId().contains(follow)){//我已经follow这个人了
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "您已关注过ta");
				return "Failed";
			}else{
				followDAO.save(follow);
				return "Succeed";
			}
		}
	}
	
	/**
	 * 去除Follow，由cUser调用，unFollow rUser
	 * @param cUser
	 * @param rUser
	 * @return
	 */
	public String unFollowSombody(Guser cUser, Guser rUser){
		Follow follow = new Follow(cUser,rUser);
		List<Follow> list = followDAO.findByExample(follow);
		if(list.size() == 0){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "您并未关注此人，请刷新页面重试");
			return "Failed";
		}else{
			for(int i=0; i<list.size(); i++){
				followDAO.delete(list.get(i));
			}
			return "Succeed";
		}
	}

	public FollowDAO getFollowDAO() {
		return followDAO;
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}
}
