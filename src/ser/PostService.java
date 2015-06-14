package ser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Favourites;
import dao.FavouritesDAO;
import dao.Game;
import dao.Guser;
import dao.Post;
import dao.PostDAO;
import dao.Section;

public class PostService {
	PostDAO postDAO;
	FavouritesDAO favouritesDAO;
	UserService userService;
	
	
	public String sendPostInSection(String title, String contents, Section sectionOfPost, Guser cUser){
		Post post = new Post();
		if(sectionOfPost != null && cUser != null){
			post.setTitle(title);
			post.setContents(contents);
			
			post.setSection(sectionOfPost);
			post.setGuser(cUser);
			
			post.setType("1");
			post.setShareNum(0);
			post.setFavouriteNum(0);
			post.setPostTime(new Timestamp(System.currentTimeMillis()));
			post.setReplies(null);
			post.setFavouriteses(null);
			
			postDAO.save(post);
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "����ʧ�ܣ���������Ϊ��û�е�¼���뷵�ص�¼������");
			return "Failed";
		}
		
	}
	
	/**!!!!!!!!!
	 * �����ض�Game��Post��������Ϸ���ܽ��棬��DAO����Ҫʵ�ְ���������������ʱ�䣬�����ö����ͣ�������Щ���򶼿���дorder by��ʵ�֣���
	 * @return
	 */
	public String getPostForSectionOrderByPostTime(Section sectionOfGame){
		List<Post> list = postDAO.findBySecionOrderByPostTime(sectionOfGame);
		if(list == null){
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("PostListOfSection", list);
			return "Succeed";
		}
	}
	/**
	 * �����ض�User��Post��ʷ�����ڸ������ģ�
	 * @return
	 */
	public List<Post> getPostForUserOrderByPostTime(Guser author){
		return postDAO.findByAuthorOrderByPostTime(author);
	}
	/**
	 * ���ڽ���һ���ض���post�в鿴;ע�ⷵ�ص�post���������һ����������Ҫ��Title����unique�����ƣ�����Ӧ����
	 * @param title
	 * @return
	 */
	public Post getPostByTitle(String title){
		return null;
	}
	/**
	 * ���ڽ���һ���ض���post�в鿴
	 * @param title
	 * @return
	 */
	public Post getPostByID(Integer ID){
		Post post = postDAO.findById(ID);
		return post;
	}
	
	/**
	 * �ղع���, �����ղ���Ϣ
	 * @param postID
	 * @param cUserAccount
	 * @return
	 */
	public String markFavourite(Integer postID, Guser cUser){
		Post post = postDAO.findById(postID);
		if(post != null && cUser != null){
			//�����ղ���Ϣ
			Favourites favourites = new Favourites(cUser, post);
			if(cUser.getFavouriteses().contains(favourites)){//�Ѿ����ղ���Ϣ��
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�ղ�ʧ�ܣ������ղظ�post����ˢ������");
				return "Failed";
			}else{
				favouritesDAO.save(favourites);
				return "Succeed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�ղ�ʧ�ܣ�������¼��ͨ��������ʽ��������");
			return "Failed";
		}
	}
	
	/**
	 * ȡ���ղع���, ɾ���ղ���Ϣ
	 * @param postID
	 * @param cUser
	 * @return
	 */
	public String unMarkFavourite(String Account,Integer postID){
		if(postID != null && Account != null){
			//ɾ���ղ���Ϣ
			List<Favourites> list = favouritesDAO.findByBelongAndPost(Account, postID);
			if(list.size() == 0){
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�ղ�ʧ�ܣ�û�������ղ���Ϣ����ˢ�����¼���");
				return "Failed";
			}else{
				//����û�������������������ظ���Ϣ��ȫ��ɾ��
				for(int i=0; i<list.size(); i++){
					favouritesDAO.delete(list.get(i));
				}
			}
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�ղ�ʧ�ܣ�������¼��ͨ��������ʽ��������");
			return "Failed";
		}
	}
	
	public List<Favourites> findFavouritesOfPost(Integer postID){
		return favouritesDAO.findByExample(new Favourites(null, postDAO.findById(postID)));
	}
	
	public String delPost(String account,Integer delPostID){
		if(userService.isAdmin(account)){
			Post post = postDAO.findById(delPostID);
			if(post == null){
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ѱ�ɾ������ˢ�����ԣ��򷵻���ҳ��");
				return "Failed";
			}else{
				postDAO.delete(post);
				return "Succeed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ǹ���Ա��лл");
			return "Failed";
		}
	}
	
	public PostDAO getPostDAO() {
		return postDAO;
	}
	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}

	public FavouritesDAO getFavouritesDAO() {
		return favouritesDAO;
	}

	public void setFavouritesDAO(FavouritesDAO favouritesDAO) {
		this.favouritesDAO = favouritesDAO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
