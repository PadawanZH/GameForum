package ser;

import java.util.List;

import dao.Game;
import dao.Post;
import dao.PostDAO;

public class PostService {
	PostDAO postDAO;
	/**!!!!!!!!!
	 * �����ض�Game��Post��������Ϸ���ܽ��棬��DAO����Ҫʵ�ְ���������������ʱ�䣬�����ö����ͣ�������Щ���򶼿���дorder by��ʵ�֣���
	 * @return
	 */
	public List<Post> getPostForGameOrderByProperty(Game game, String orderByProperty){
		return null;
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
}
