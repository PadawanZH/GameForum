package ser;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Game;
import dao.Guser;
import dao.Post;
import dao.PostDAO;
import dao.Section;

public class PostService {
	PostDAO postDAO;
	
	
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
			System.out.println("one post saved");
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "����ʧ�ܣ��뷵������");
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
	 * ���ڽ���һ���ض���post�в鿴;ע�ⷵ�ص�post���������һ����������Ҫ��Title����unique�����ƣ�����Ӧ����
	 * @param title
	 * @return
	 */
	public Post getPostByTitle(String title){
		return null;
	}
	
	public String markFavourite(Integer postID){
		Post post = postDAO.findById(postID);
		if(post != null){
			post.setFavouriteNum(post.getFavouriteNum() + 1);
			return "Success";
		}else{
			return "Failed";
		}
	}
	public PostDAO getPostDAO() {
		return postDAO;
	}
	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
}
