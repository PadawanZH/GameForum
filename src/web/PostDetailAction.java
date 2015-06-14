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
	String contents;//存放回复内容
	Game gameToAdd;//被添加的游戏对象，通过表单赋值
	
	/**
	 * 从前端GamePage接收url参数postID，将curPost存入session，将post的reply存入session
	 * @return
	 */
	public String getInPostDetail(){
		String chosedPostID = ServletActionContext.getRequest().getParameter("chosedPostID");
		Post curPost = postService.getPostByID(Integer.parseInt(chosedPostID));
		if(curPost == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "当前帖子已被删除，请返回页面刷新列表");
			return "Failed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("curPost", curPost);
		}
		
		List<Reply> list = replyService.getReplyInPostbyTime(curPost);
		ServletActionContext.getRequest().getSession().setAttribute("replyListOfPost", list);
		
		//刷新favourites
		List<Favourites> whoFavouritePost = postService.findFavouritesOfPost(curPost.getId());
		ServletActionContext.getRequest().getSession().setAttribute("whoFavouritePost", whoFavouritePost);
		return "Succeed";
	}
	
	/**
	 * 在postdetail页面发送reply调用，更新replyListOfPost
	 * @return
	 */
	public String sendReplyInPost(){
		//获得session中section，guser
		Post curPost = (Post) ServletActionContext.getRequest().getSession().getAttribute("curPost");
		Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
		//存储回复
		String status = replyService.addReply(cUser, curPost, contents);
		//刷新reply列表
		List<Reply> list = replyService.getReplyInPostbyTime(curPost);
		ServletActionContext.getRequest().getSession().setAttribute("replyListOfPost", list);
		
		return status;
	}
	
	/**
	 * postdetail页面收藏功能,并更新session中curPost信息
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
	 * postdetail页面取消收藏功能,并更新session中curPost信息
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
	 * 删除post，管理员才可以，在ser层检测， 该action可通过url链接直接访问
	 * @return Succeed转到gamepage页面，Failed转到错误页面
	 */
	public String delPost(){
		String delPostID = ServletActionContext.getRequest().getParameter("delPostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		
		return postService.delPost(cUserAccount, Integer.parseInt(delPostID));
	}
	
	/**
	 * 删除reply，管理员才可以，在ser层检测
	 * @return Succeed转到postdetail页面，Failed转到错误页面，注意，这个只有表单提交方式才能调用
	 */
	public String delReply(){
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		String delReplyID = ServletActionContext.getRequest().getParameter("delReplyID");
		
		String status = replyService.delReply(cUserAccount, Integer.parseInt(delReplyID));
		
		//刷新reply列表
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
