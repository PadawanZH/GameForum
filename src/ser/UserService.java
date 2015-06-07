package ser;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import dao.Guser;
import dao.GuserDAO;
import dao.Usergroup;

public class UserService {
	
	private GuserDAO guserDAO;
	String nullString = new String("");
	
	/**
	 * ��֤�û���Ϣ��ά��session�е�cUser��cUserType
	 * @param account
	 * @param passwd
	 * @return	���ֽ�� 1. NotFound û�ҵ�<p>2. Succeed �ɹ���½<p>3. WrongPasswd �������<p>
	 */
	public String login(String account, String passwd){
		String status = "";
		status = guserCheck(account,passwd);
		if(status == "Succeed"){
			Guser cuser = guserDAO.findById(account);
			ServletActionContext.getRequest().getSession().setAttribute("cUser", cuser);
			ServletActionContext.getRequest().getSession().setAttribute("cUserType", cuser.getUsergroup().getName());
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("cUser", null);
			ServletActionContext.getRequest().getSession().setAttribute("cUserType", "vister");
		}
		return status;
	}
	
	public void logoff(String account){
		ServletActionContext.getRequest().getSession().setAttribute("cUser", null);
	}
	/**
	 * ���������Ϊ����Ա�������߲��ܽ�֮����Ϊ����Ա�û���
	 * @param account
	 * @param name
	 * @param passwd
	 * @return ���ֽ��
	 * 			1. NotAdmin �����߲��ǹ���Ա<p>
	 * 			2. Existed �ظ�����Ա�û�<p>
	 * 			3. Succeed �洢�ɹ�<p>
	 */
	public String adminRegist(Guser Introducer, String targerAccount){
		String status = "";
		if(!isAdmin(Introducer.getAccount()) ){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ǹ���Ա���޴�Ȩ��");
			return "NotAdmin";
		}
		Guser targetUser = guserDAO.findById(targerAccount);
		//����
		if(targetUser.getUsergroup().getName() == "admin"){//ÿһ��admin����һ����ͬ��user
			status = "Existed";
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "���û��Ѿ��ǹ���Ա�������û���");
		}else{
			targetUser.setUsergroup(new Usergroup("admin"));
			guserDAO.save(targetUser);
			status = "Succeed";
		}
		return status;
	}
	/**
	 * 
	 * @param account
	 * @param name
	 * @param passwd
	 * @param gender
	 * @param birthday
	 * @param email
	 * @param groupId
	 * @param points
	 * @param postNum
	 * @param replyNum
	 * @param signature
	 * @param coinNum
	 * @return ���ֽ��
	 * 			1. Existed �ظ�����Ա�û�<p>
	 * 			2. SaveFailed �洢ʧ��<p>
	 * 			3. Succeed �洢�ɹ�<p>
	 */
	public String userRegist(Guser guser){
		String status = "";
		//����
		if(guserDAO.findById(guser.getAccount()) != null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�Բ����û����Ѵ��ڣ��뷵������");
			status = "Existed";
		}else{
			try{
				guser.setCoinNum(0);
				guser.setUsergroup(new Usergroup("С��"));
				guser.setPoints(0);
				guser.setPostNum(0);
				guser.setReplyNum(0);
				
				guserDAO.save(guser);
			}catch(RuntimeException re){
				status = "SaveFailed";
			}
			status = "Succeed";
		}
		return status;
	}
	/**
	 * <b> guser </b>�ı����� 
	 * @param account
	 * @param oldPasswd
	 * @param newPasswd
	 * @return 1. Succeed 2. ChangeFailed
	 */
	public String changePasswd(String account, String oldPasswd, String newPasswd){
		String status = "";
		if(guserCheck(account, oldPasswd) == "Succeed"){
			Guser guser = guserDAO.findById(account);
			guser.setPasswd(newPasswd);
			guserDAO.attachDirty(guser);
			status = "Succeed";
		}else{
			status = "ChangeFailed";
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�޸�ʧ�ܣ����������벻�ԣ��뷵����������");
		}
		return status;
	}
	
	/**
	 * guser��Ϣ�ĸ��·���
	 * @param newInstanceer
	 * @return Succeed,ChangeFailed
	 */
	public String changeInfo(Guser newInstanceer){
		String status = "";
		if(guserCheck(newInstanceer.getAccount(), newInstanceer.getPasswd()) == "Succeed"){
			Guser oldInstance = guserDAO.findById(newInstanceer.getAccount());
			mergeUserInfo(newInstanceer, oldInstance);
			guserDAO.attachDirty(oldInstance);
			status = "Succeed";
		}else{
			status = "ChangeFailed";
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�޸�ʧ�ܣ����������벻�ԣ��뷵����������");
		}
		return status;
	}
	/**
	 * ���¸ı���Ϣҳ���ֵ��ͷ��û��
	 * @param newInstance
	 * @param target
	 */
	private void mergeUserInfo(Guser newInstance,Guser target){
		if(!newInstance.getEmail().equals(nullString)){
			target.setEmail(newInstance.getEmail());
		}
		if(newInstance.getGender() != null){
			target.setGender(newInstance.getGender());
		}
		if(newInstance.getBirthday() != null){
			target.setBirthday(newInstance.getBirthday());
		}
		if(!newInstance.getSignature().equals(nullString)){
			target.setSignature(newInstance.getSignature());
		}
	}
	
	/**
	 * �ڲ��������û���¼����login�е���
	 * @param account
	 * @param passwd
	 * @return
	 */
	public String guserCheck(String account, String passwd){
		Guser guser = guserDAO.findById(account);
		String status = "";
		
		if(guser == null){
			status = "NotFound";
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�Ҳ����û������뷵������");

		}else{
			if(guser.getPasswd().equals(passwd)){
				status = "Succeed";
			}else{
				status = "WrongPasswd";
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "��������뷵������");
			}
		}
		return status;
	}
	/**
	 * һ������ҳ���ʼ��ʱ�����û���Ϣ�����û���Ϣ����session��
	 * @param account
	 * @return true �ɹ��ҵ� false ����ʧ��
	 */
	public boolean RequestCurUserDataWithAccount(String account){
		Guser guser = guserDAO.findById(account);
		if(guser != null){
			ServletActionContext.getRequest().getSession().setAttribute("cUser", guser);
			ServletActionContext.getRequest().getSession().setAttribute("cUserType", guser.getUsergroup().getName());
			return true;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "������Ϣʧ�ܣ������½�����վ");
			return false;
		}
	}
	
	/**
	 * ѯ���Ƿ��ǹ���Ա
	 * @return
	 */
	public boolean isAdmin(String account){
		return guserDAO.findById(account).getUsergroup().getName() == "admin";
	}
	/**
	 * ���Ժ���
	 * @return
	 */
	public Guser getUserInfo(String account){
		return guserDAO.findById(account);
	}

	/**
	 * @return the guserDAO
	 */
	public GuserDAO getGuserDAO() {
		return guserDAO;
	}
	/**
	 * @param guserDAO the guserDAO to set
	 */
	public void setGuserDAO(GuserDAO guserDAO) {
		this.guserDAO = guserDAO;
	}
}
