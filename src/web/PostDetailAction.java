package web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

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
	String contents;
	
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
		Post newCurPost = postService.getPostByID(Integer.parseInt(favouritePostID));
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = postService.markFavourite(newCurPost, cUser);
		
		if(status == "Succeed"){
			//更新curPost以更新在curPost中的Favourites信息，注意，若favouritePostID不是合法值，在status=failed，不会进入次代码段，不必担心设置curPost为null
			ServletActionContext.getRequest().getSession().setAttribute("curPost", newCurPost);
			ServletActionContext.getRequest().getSession().setAttribute("cUser", cUser);
		}
		return status;
	}
	
	/**
	 * postdetail页面取消收藏功能,并更新session中curPost信息
	 * @return
	 */
	public String unFavouritePost(){
		String favouritePostID = ServletActionContext.getRequest().getParameter("favouritePostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		Post newCurPost = postService.getPostByID(Integer.parseInt(favouritePostID));
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = postService.unMarkFavourite(Integer.parseInt(favouritePostID), cUser);
		if(status == "Succeed"){
			//更新curPost以更新在curPost中的Favourites信息，注意，若favouritePostID不是合法值，在status=failed，不会进入次代码段，不必担心设置curPost为null
			ServletActionContext.getRequest().getSession().setAttribute("curPost", newCurPost);
			ServletActionContext.getRequest().getSession().setAttribute("cUser", cUser);
		}
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
}
