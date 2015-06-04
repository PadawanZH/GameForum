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
	 * @return
	 */
	public String changeInfo(Guser newInstanceer){
		String status = "";
		if(guserCheck(newInstanceer.getAccount(), newInstanceer.getPasswd()) == "Succeed"){
			Guser oldInstance = guserDAO.findById(newInstanceer.getAccount());
			guserDAO.merge(newInstanceer);
			guserDAO.save(oldInstance);
			status = "Succeed";
		}else{
			status = "ChangeFailed";
		}
		return status;
	}
	
	/**
	 * �ڲ�����������Ա��¼����login�е���
	 * @param account
	 * @param passwd
	 * @return
	 */
	private String adminCheck(String account, String passwd){
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
	private String guserCheck(String account, String passwd){
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
