package ser;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Follow;
import dao.FollowDAO;
import dao.Guser;

public class FollowerService {
	FollowDAO followDAO;
	
	/**
	 * ���Follow,��cUser���ã�follow rUser
	 * @param myAccount
	 * @param targetAccount
	 * @return
	 */
	public String followSomebody(Guser cUser, Guser rUser){
		if(cUser == null || rUser == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "��עʧ�ܣ����¼");
			return "Failed";
		}
		if( cUser.getAccount().equals(rUser.getAccount())){//follow�����Լ�
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "��עʧ�ܣ�����ע�����Լ�");
			return "Failed";
		}else{
			Follow follow = new Follow(cUser, rUser);
			followDAO.save(follow);
			return "Succeed";
		}
	}
	
	/**
	 * ȥ��Follow����cUser���ã�unFollow rUser
	 * @param cUser
	 * @param rUser
	 * @return
	 */
	public String unFollowSombody(Guser cUser, Guser rUser){
		Follow follow = new Follow(cUser,rUser);
		List<Follow> list = followDAO.findByExample(follow);
		if(list.size() != 1){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "����δ��ע���ˣ���ˢ��ҳ������");
			return "Failed";
		}else{
			followDAO.delete(list.get(0));
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
