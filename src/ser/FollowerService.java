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
			
			if(cUser.getFollowsForFromId().contains(follow)){//���Ѿ�follow�������
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "���ѹ�ע��ta");
				return "Failed";
			}else{
				followDAO.save(follow);
				return "Succeed";
			}
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
		List<Follow> list = followDAO.findByFromAndTarget(cUser.getAccount(), rUser.getAccount());
		if(list.size() == 0){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "����δ��ע���ˣ���ˢ��ҳ������");
			return "Failed";
		}else{
			for(int i=0; i<list.size(); i++){
				followDAO.delete(list.get(i));
			}
			return "Succeed";
		}
	}
	
	public List<Follow> getFollow(String propertyName, String value){
		return followDAO.findByProperty(propertyName, value);
	}

	public FollowDAO getFollowDAO() {
		return followDAO;
	}

	public void setFollowDAO(FollowDAO followDAO) {
		this.followDAO = followDAO;
	}
}
