package web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Favourites;
import dao.Game;
import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.Section;
import ser.PostService;
import ser.ReplyService;
import ser.UserService;

public class PostDetailAction {
	PostService postService;
	ReplyService replyService;
	UserService userService;
	String contents;//��Żظ�����
	Game gameToAdd;//����ӵ���Ϸ����ͨ������ֵ
	
	/**
	 * ��ǰ��GamePage����url����postID����curPost����session����post��reply����session
	 * @return
	 */
	public String getInPostDetail(){
		String chosedPostID = ServletActionContext.getRequest().getParameter("chosedPostID");
		Post curPost = postService.getPostByID(Integer.parseInt(chosedPostID));
		if(curPost == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "��ǰ�����ѱ�ɾ�����뷵��ҳ��ˢ���б�");
			return "Failed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("curPost", curPost);
		}
		
		List<Reply> list = replyService.getReplyInPostbyTime(curPost);
		ServletActionContext.getRequest().getSession().setAttribute("replyListOfPost", list);
		
		//ˢ��favourites
		List<Favourites> whoFavouritePost = postService.findFavouritesOfPost(curPost.getId());
		ServletActionContext.getRequest().getSession().setAttribute("whoFavouritePost", whoFavouritePost);
		return "Succeed";
	}
	
	/**
	 * ��postdetailҳ�淢��reply���ã�����replyListOfPost
	 * @return
	 */
	public String sendReplyInPost(){
		//���session��section��guser
		Post curPost = (Post) ServletActionContext.getRequest().getSession().getAttribute("curPost");
		Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
		//�洢�ظ�
		String status = replyService.addReply(cUser, curPost, contents);
		//ˢ��reply�б�
		List<Reply> list = replyService.getReplyInPostbyTime(curPost);
		ServletActionContext.getRequest().getSession().setAttribute("replyListOfPost", list);
		
		return status;
	}
	
	/**
	 * postdetailҳ���ղع���,������session��curPost��Ϣ
	 * @return Failed Succeed
	 */
	public String favouritePost(){
		
		String favouritePostID = ServletActionContext.getRequest().getParameter("favouritePostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = postService.markFavourite(Integer.parseInt(favouritePostID), cUser);
		
		List<Favourites> whoFavouritePost = postService.findFavouritesOfPost(Integer.parseInt(favouritePostID));
		ServletActionContext.getRequest().getSession().setAttribute("whoFavouritePost", whoFavouritePost);
		
		return status;
	}
	
	/**
	 * postdetailҳ��ȡ���ղع���,������session��curPost��Ϣ
	 * @return
	 */
	public String unFavouritePost(){
		String favouritePostID = ServletActionContext.getRequest().getParameter("favouritePostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		
		String status = postService.unMarkFavourite(cUserAccount, Integer.parseInt(favouritePostID));
		
		List<Favourites> whoFavouritePost = postService.findFavouritesOfPost(Integer.parseInt(favouritePostID));
		ServletActionContext.getRequest().getSession().setAttribute("whoFavouritePost", whoFavouritePost);
		
		return status;
	}
	
	/**
	 * ɾ��post������Ա�ſ��ԣ���ser���⣬ ��action��ͨ��url����ֱ�ӷ���
	 * @return Succeedת��gamepageҳ�棬Failedת������ҳ��
	 */
	public String delPost(){
		String delPostID = ServletActionContext.getRequest().getParameter("delPostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		
		return postService.delPost(cUserAccount, Integer.parseInt(delPostID));
	}
	
	/**
	 * ɾ��reply������Ա�ſ��ԣ���ser����
	 * @return Succeedת��postdetailҳ�棬Failedת������ҳ�棬ע�⣬���ֻ�б��ύ��ʽ���ܵ���
	 */
	public String delReply(){
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		String delReplyID = ServletActionContext.getRequest().getParameter("delReplyID");
		
		String status = replyService.delReply(cUserAccount, Integer.parseInt(delReplyID));
		
		//ˢ��reply�б�
		Post curPost = (Post) ServletActionContext.getRequest().getSession().getAttribute("curPost");
		List<Reply> list = replyService.getReplyInPostbyTime(curPost);
		ServletActionContext.getRequest().getSession().setAttribute("replyListOfPost", list);
		return status;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public ReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Game getGameToAdd() {
		return gameToAdd;
	}

	public void setGameToAdd(Game gameToAdd) {
		this.gameToAdd = gameToAdd;
	}
}
