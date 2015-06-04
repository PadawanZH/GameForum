package ser;

import java.util.List;

import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.ReplyDAO;

public class ReplyService {
	ReplyDAO replyDAO;
	
	/**
	 * 得到post的回复，根据时间排序
	 * @param post
	 * @return
	 */
	public List<Reply> getReplyInPost(Post post){
		return null;
	}
	
	/**
	 * 添加回复
	 * @return
	 */
	public String addReply(Guser cUser, Reply reply){
		return null;
	}
	/**
	 *	删除reply，可能只有版主，回复者、管理员可以删除，所以要检查权限，并作出处理
	 * @param reply
	 * @return
	 */
	public String deleteReply(Guser cUser, Reply reply){
		return null;
	}
	/**
	 * 检查删除时的权限是否可以删除
	 * @return
	 */
	private boolean checkDeletePermission(){
		return false;
	}
	
	
}
