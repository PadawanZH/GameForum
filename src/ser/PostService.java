package ser;

import java.util.List;

import dao.Game;
import dao.Post;
import dao.PostDAO;

public class PostService {
	PostDAO postDAO;
	/**!!!!!!!!!
	 * 返回特定Game的Post，用于游戏介绍界面，在DAO类中要实现安点赞数量（或是时间，或是置顶类型）排序（这些排序都可以写order by简单实现），
	 * @return
	 */
	public List<Post> getPostForGameOrderByProperty(Game game, String orderByProperty){
		return null;
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
}
