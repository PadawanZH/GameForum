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
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "发帖失败，可能是因为您没有登录，请返回登录并重试");
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
	 * 返回特定User的Post历史，用于个人中心，
	 * @return
	 */
	public List<Post> getPostForUserOrderByPostTime(Guser author){
		return postDAO.findByAuthorOrderByPostTime(author);
	}
	/**
	 * 用于进入一个特定的post中查看;注意返回的post不允许多余一个，可能需要对Title做出unique的限制，并相应处理
	 * @param title
	 * @return
	 */
	public Post getPostByTitle(String title){
		return null;
	}
	/**
	 * 用于进入一个特定的post中查看
	 * @param title
	 * @return
	 */
	public Post getPostByID(Integer ID){
		Post post = postDAO.findById(ID);
		return post;
	}
	
	/**
	 * 收藏功能, 存入收藏信息
	 * @param postID
	 * @param cUserAccount
	 * @return
	 */
	public String markFavourite(Post post, Guser cUser){
		if(post != null && cUser != null){
			//存入收藏信息
			Favourites favourites = new Favourites(cUser, post);
			if(cUser.getFavouriteses().contains(favourites)){//已经有收藏信息了
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "收藏失败，您已收藏该post，请刷新重试");
				return "Failed";
			}else{
				favouritesDAO.save(favourites);
				return "Succeed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "收藏失败，请您登录或通过正常方式访问帖子");
			return "Failed";
		}
	}
	
	/**
	 * 取消收藏功能, 删除收藏信息
	 * @param postID
	 * @param cUser
	 * @return
	 */
	public String unMarkFavourite(Integer postID, Guser cUser){
		Post post = postDAO.findById(postID);
		if(post != null && cUser != null){
			//删除收藏信息
			List<Favourites> list = favouritesDAO.findByExample( new Favourites(cUser, post));
			if(list.size() == 0){
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "收藏失败，没有您的收藏信息，请刷新重新检视");
				return "Failed";
			}else{
				//由于没有联合主键，可能有重复信息，全部删除
				for(int i=0; i<list.size(); i++){
					favouritesDAO.delete(list.get(i));
				}
			}
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "收藏失败，请您登录或通过正常方式访问帖子");
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
}
