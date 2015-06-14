package web;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Follow;
import dao.Game;
import dao.Guser;
import dao.Message;
import dao.Post;
import dao.Reply;
import dao.Section;
import ser.FollowerService;
import ser.GameInfoService;
import ser.MessageService;
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
	MessageService messageService;
	Game game;
	String gameIDToDel;//删除游戏用
	String cUserAccount;
	String rUserAccount;
	
	Message message;
	String receiveAccount;
	String sendAccount;
	
	
	 
	/**
	 * 查看他人信息时调用，
	 * 将他人信息存到session的“rUser”中，关注，收藏一并在rUser成员中
	 * 将他的发帖存到session的“rUserPosts”中，按时间排序
	 * 将他的回复存到session的“rUserReplys”中，
	 * @return
	 */
	public String lookForOther(){
		String accountToLook = ServletActionContext.getRequest().getParameter("accountToLook");
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
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", accountToLook);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", accountToLook);
		ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
		ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		if(userService.isAdmin(accountToLook)){
			Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
			if(cUser.getAccount().equals(accountToLook)){//管理员进入自己的个人中心，将所有游戏装入“allGameList”
				gameInfoService.findAllGame();
			}
		}
		return "Succeed";
	}
	
	/**
	 * 在个人中心页面，关注按钮调用，添加follow，
	 * @return
	 */
	public String followSomeone(){

		Guser cUser = userService.getUserInfo(cUserAccount);
		Guser rUser = userService.getUserInfo(rUserAccount);
		String status = followerService.followSomebody(cUser, rUser);
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", rUserAccount);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", rUserAccount);
		if(status.equals("Succeed")){
			//刷新当前用户信息
			ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
			ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		}
		return status;
	}
	
	/**
	 * 在个人中心页面，关注按钮调用，取消follow，
	 * @return
	 */
	public String unFollowSomeone(){
		Guser cUser = userService.getUserInfo(cUserAccount);
		Guser rUser = userService.getUserInfo(rUserAccount);
		String status = followerService.unFollowSombody(cUser, rUser);
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", rUserAccount);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", rUserAccount);
		if(status.equals("Succeed")){
			//刷新当前用户信息
			ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
			ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		}
		return status;
	}
	
	/**
	 * 管理员删除他人时使用,使用url访问action
	 * @return
	 */
	public String deleteUser(){
		String accountToDelete = ServletActionContext.getRequest().getParameter("accountToDelete");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = userService.deleteUser(cUser, accountToDelete);
		return status;
	}
	
	public String promoteAdmin(){
		String status = userService.adminRegist(cUserAccount, rUserAccount);
		Guser cUser = userService.getUserInfo(cUserAccount);
		Guser rUser = userService.getUserInfo(rUserAccount);
		ServletActionContext.getRequest().getSession().setAttribute("cUser", cUser);
		ServletActionContext.getRequest().getSession().setAttribute("rUser", rUser);
		return status;
	}
	
	
	/**
	 * admin的personalCenter中调用，添加游戏，相关的Section在database中的trigger中处理，<p>
	 * 使用表单form访问action <p>
	 * gameInfoService中函数还未实现
	 * @return
	 */
	public String addGame(){
		Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
		if(cUser != null){
			if(userService.isAdmin(cUser.getAccount())){
				
				//System.out.println("PersonalInfoAction.addGame() file Path : "+ headerPicture.getAbsolutePath());
				
				//gameInfoService.addGame(game);
				
				return "Succeed";
			}else{
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "您不是管理员，无法添加");
				return "Failed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "请您登录");
			return "Failed";
		}
		
	}
	/**
	 * admin的personalCenter中调用，删除游戏，相关的Section在database中的trigger中处理
	 * @return
	 */
	public String delGame(){
		String status = gameInfoService.delGame(Integer.parseInt(gameIDToDel));
		//刷新allGameList
		gameInfoService.findAllGame();
		return status;
	}
	
	/**
	 * 发送消息，还有个更新messagelist我没写
	 * @return
	 */
	public String sendMessage(){
		Guser receiver = userService.getUserInfo(receiveAccount);
		Guser sender = userService.getUserInfo(sendAccount);
		if(receiver != null && sender != null){
			message.setGuserBySender(sender);
			message.setGuserByReceiver(receiver);
			message.setMarked("Y");
			message.setTime(new Timestamp(System.currentTimeMillis()));
			
			messageService.SendMessage(message);
			//更新信息列表
			List<Message> list = messageService.getMessageAsSender(sendAccount);
			ServletActionContext.getRequest().getSession().setAttribute("SenMessageList", list);
			System.out.println("PersonalInfoAction.sendMessage() + sendlist.size : "+list.size() );
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "无此用户，请您确认后再发");
			return "Failed";
		}
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


	public String getGameIDToDel() {
		return gameIDToDel;
	}

	public void setGameIDToDel(String gameIDToDel) {
		this.gameIDToDel = gameIDToDel;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public String getCUserAccount() {
		return cUserAccount;
	}

	public void setCUserAccount(String cUserAccount) {
		this.cUserAccount = cUserAccount;
	}

	public String getRUserAccount() {
		return rUserAccount;
	}

	public void setRUserAccount(String rUserAccount) {
		this.rUserAccount = rUserAccount;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public String getSendAccount() {
		return sendAccount;
	}

	public void setSendAccount(String sendAccount) {
		this.sendAccount = sendAccount;
	}

}
