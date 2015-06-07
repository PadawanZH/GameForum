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
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "发帖失败，请返回重试");
			return "Failed";
		}
		
	}
	
	/**!!!!!!!!!
	 * 返回特定Game的Post，用于游戏介绍界面，在DAO类中要实现安点赞数量（或是时间，或是置顶类型）排序（这些排序都可以写order by简单实现），
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
	 * 用于进入一个特定的post中查看;注意返回的post不允许多余一个，可能需要对Title做出unique的限制，并相应处理
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
