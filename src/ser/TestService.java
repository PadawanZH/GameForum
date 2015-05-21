package ser;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.TUser;
import dao.TUserDAO;

public class TestService {
	TUserDAO tUserDAO;
	public List<TUser> Test(){
		return tUserDAO.findAll();
	}
	/**
	 * @return the tUserDAO
	 */
	public TUserDAO gettUserDAO() {
		return tUserDAO;
	}
	/**
	 * @param tUserDAO the tUserDAO to set
	 */
	public void settUserDAO(TUserDAO tUserDAO) {
		this.tUserDAO = tUserDAO;
	}
}
