package ser;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import dao.Admin;
import dao.AdminDAO;
import dao.Guser;
import dao.GuserDAO;

public class UserService {
	
	private AdminDAO adminDAO;
	private GuserDAO guserDAO;
	String nullString = new String("");
	
	/**
	 * 
	 * @param account
	 * @param passwd
	 * @return	���ֽ�� 1. NotFound û�ҵ�<p>2. Succeed �ɹ���½<p>3. WrongPasswd �������<p>
	 */
	public String login(String account, String passwd){
		String status = "";
		String type = "";//��ʶ����ǹ���Ա�����û������崫ֵ��ʽ�д��޸ģ�����������
		status = adminCheck(account,passwd);
		if(status != "NotFound"){
			type = "admin";	
		}else{
			status = guserCheck(account,passwd);
			if(status != "NotFound"){
				type = "guser";
			}else{
				return "NotFound";
			}
		}
		
		ServletActionContext.getRequest().getSession().setAttribute("cUser", guserDAO.findById(account));
		ServletActionContext.getRequest().getSession().setAttribute("cUserType", type);
		return status;
	}
	/**
	 * 
	 * @param account
	 * @param name
	 * @param passwd
	 * @return ���ֽ��
	 * 			1. Existed �ظ�����Ա�û�<p>
	 * 			2. SaveFailed �洢ʧ��<p>
	 * 			3. Succeed �洢�ɹ�<p>
	 */
	public String adminRegist(String account, String name, String passwd){
		String status = "";
		//����
		if(guserDAO.findById(account) != null){//ÿһ��admin����һ����ͬ��user
			status = "Existed";
		}else{
			Admin admin = new Admin(account,name,passwd);
			try{
				adminDAO.save(admin);
			}catch(RuntimeException re){
				status = "SaveFailed";
			}
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
			status = "Existed";
		}else{
			try{
				guser.setCoinNum(0);
				guser.setGroupId(0);
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
		}
		return status;
	}
	
	/**
	 * guser��Ϣ�ĸ��·�����<b><font color="red">��ȷ���д���֤������</font></b>
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
	 * �ڲ�����������Ա��¼����login�е���
	 * @param account
	 * @param passwd
	 * @return
	 */
	public String adminCheck(String account, String passwd){
		Admin admin = adminDAO.findById(account);
		String status = "";
		
		if(admin == null){
			status = "NotFound";
		}else{
			if(admin.getPasswd().equals(passwd)){
				status = "Succeed";
			}else{
				status = "WrongPasswd";
			}
		}
		return status;
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
		}else{
			if(guser.getPasswd().equals(passwd)){
				status = "Succeed";
			}else{
				status = "WrongPasswd";
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
			ServletActionContext.getRequest().getSession().setAttribute("UserData", guser);
			return true;
		}else{
			return false;
		}
	}


	/**
	 * @return the adminDAO
	 */
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}
	/**
	 * @param adminDAO the adminDAO to set
	 */
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
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
