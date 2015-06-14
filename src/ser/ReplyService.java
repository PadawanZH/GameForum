package ser;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.ReplyDAO;

public class ReplyService {
	ReplyDAO replyDAO;
	PostService postService;
	UserService userService;
	
	/**
	 * 得到post的回复，根据时间升序排序,一般在post页面初始化时使用（包括点击回复后）
	 * @param post
	 * @return
	 */
	public List<Reply> getReplyInPostbyTime(Post post){
		List<Reply> list;
		try{
			list = replyDAO.findByPostOrderByReplyTime(post);
			//过会试一下将之在service层存入session，而不是返回给action
			return list;
		}catch (RuntimeException re) {
			System.out.println("attr name is wrong in ReplyService.getReplyInPost.findByAOrderByB");
		}
		return null;
	}
	
	/**
	 * 返回特定User的Post历史，用于个人中心，
	 * @return
	 */
	public List<Reply> getPostForUserOrderByPostTime(Guser author){
		return replyDAO.findByUserOrderByReplyTime(author);
	}
	
	/**
	 * 添加回复，需要用户，和当前帖子对象
	 * @return
	 */
	public String addReply(Guser cUser, Post curPost, String contents){
		Reply reply = new Reply();
		if(cUser != null && curPost != null){
			reply.setGuser(cUser);
			reply.setPost(curPost);
			
			reply.setFloor(0);
			reply.setFavouriteNum(0);
			reply.setPostTime(new Timestamp(System.currentTimeMillis()));
			reply.setContents(contents);
			replyDAO.save(reply);
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "评论失败，可能是因为您没有登录，请返回登录并重试");
			return "Failed";
		}
		
	}
	
	/**
	 *	删除reply，可能只有管理员可以删除，所以要检查权限，并作出处理
	 * @param reply
	 * @return
	 */
	public String delReply(String account,Integer delReplyID){
		if(userService.isAdmin(account)){
			Reply reply = replyDAO.findById(delReplyID);
			if(reply == null){
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "此帖已被删除，请刷新重试，或返回主页面");
				return "Failed";
			}else{
				replyDAO.delete(reply);
				return "Succeed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "您不是管理员，谢谢");
			return "Failed";
		}
	}
	
	/**
	 * 检查删除时的权限是否可以删除
	 * @return
	 */
	private boolean checkDeletePermission(){
		return false;
	}

	public ReplyDAO getReplyDAO() {
		return replyDAO;
	}


	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}


	public PostService getPostService() {
		return postService;
	}


	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
