package web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Game;
import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.Section;
import ser.FollowerService;
import ser.GameInfoService;
import ser.PostService;
import ser.ReplyService;
import ser.UserService;

/**
 * 处理personalCenter的各种操作
 * @author Administrator
 *
 */
public class PersonalInfoAction {

	UserService userService;
	PostService postService;
	ReplyService replyService;
	GameInfoService gameInfoService;
	FollowerService followerService;
	Game game;
	
	/**
	 * 查看他人信息时调用，
	 * 将他人信息存到session的“rUser”中，关注，收藏一并在rUser成员中
	 * 将他的发帖存到session的“rUserPosts”中，按时间排序
	 * 将他的回复存到session的“rUserReplys”中，
	 * @return
	 */
	public String lookForOther(){
		String accountToLook = ServletActionContext.getRequest().getParameter("accountToLook");
		System.out.println("PersonalInfoAction.lookForOther() : accountToLook = "+accountToLook);
		Guser rUser = userService.getUserInfo(accountToLook);
		if(rUser == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "您选择的用户已因为不知名原因退出论坛");
			return "Failed";
		}else{
			//rUser中自带关注followsForTargetId，关注favouriteses
			ServletActionContext.getRequest().getSession().setAttribute("rUser", rUser);
		}
		//按时间排序的post
		List<Post> rUserPosts = postService.getPostForUserOrderByPostTime(rUser);
		ServletActionContext.getRequest().getSession().setAttribute("rUserPosts", rUserPosts);
		//按时间排序的replys
		List<Reply> rUserReplys = replyService.getPostForUserOrderByPostTime(rUser);
		ServletActionContext.getRequest().getSession().setAttribute("rUserReplys", rUserReplys);
		return "Succeed";
	}
	
	/**
	 * 在个人中心页面，关注按钮调用，添加follow，
	 * @return
	 */
	public String followSomeone(){
		Guser cUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("cUser");
		Guser rUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("rUser");
		String status = followerService.followSomebody(cUser, rUser);
		
		return status;
	}
	
	/**
	 * 在个人中心页面，关注按钮调用，取消follow，
	 * @return
	 */
	public String unFollowSomeone(){
		Guser cUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("cUser");
		Guser rUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("rUser");
		String status = followerService.unFollowSombody(cUser, rUser);
		if(status == "Succeed"){
			//刷新当前用户信息
			ServletActionContext.getRequest().getSession().setAttribute("cUser", userService.getUserInfo(cUser.getAccount()));
		}
		return status;
	}
	
	/**
	 * 相关的Section在database中的trigger中处理
	 * @return
	 */
	public String addGame(){
		return null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public GameInfoService getGameInfoService() {
		return gameInfoService;
	}

	public void setGameInfoService(GameInfoService gameInfoService) {
		this.gameInfoService = gameInfoService;
	}

	public FollowerService getFollowerService() {
		return followerService;
	}

	public void setFollowerService(FollowerService followerService) {
		this.followerService = followerService;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
