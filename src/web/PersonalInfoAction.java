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
 * ����personalCenter�ĸ��ֲ���
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
	 * �鿴������Ϣʱ���ã�
	 * ��������Ϣ�浽session�ġ�rUser���У���ע���ղ�һ����rUser��Ա��
	 * �����ķ����浽session�ġ�rUserPosts���У���ʱ������
	 * �����Ļظ��浽session�ġ�rUserReplys���У�
	 * @return
	 */
	public String lookForOther(){
		String accountToLook = ServletActionContext.getRequest().getParameter("accountToLook");
		System.out.println("PersonalInfoAction.lookForOther() : accountToLook = "+accountToLook);
		Guser rUser = userService.getUserInfo(accountToLook);
		if(rUser == null){
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "��ѡ����û�����Ϊ��֪��ԭ���˳���̳");
			return "Failed";
		}else{
			//rUser���Դ���עfollowsForTargetId����עfavouriteses
			ServletActionContext.getRequest().getSession().setAttribute("rUser", rUser);
		}
		//��ʱ�������post
		List<Post> rUserPosts = postService.getPostForUserOrderByPostTime(rUser);
		ServletActionContext.getRequest().getSession().setAttribute("rUserPosts", rUserPosts);
		//��ʱ�������replys
		List<Reply> rUserReplys = replyService.getPostForUserOrderByPostTime(rUser);
		ServletActionContext.getRequest().getSession().setAttribute("rUserReplys", rUserReplys);
		return "Succeed";
	}
	
	/**
	 * �ڸ�������ҳ�棬��ע��ť���ã����follow��
	 * @return
	 */
	public String followSomeone(){
		Guser cUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("cUser");
		Guser rUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("rUser");
		String status = followerService.followSomebody(cUser, rUser);
		
		return status;
	}
	
	/**
	 * �ڸ�������ҳ�棬��ע��ť���ã�ȡ��follow��
	 * @return
	 */
	public String unFollowSomeone(){
		Guser cUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("cUser");
		Guser rUser = (Guser) ServletActionContext.getRequest().getSession().getAttribute("rUser");
		String status = followerService.unFollowSombody(cUser, rUser);
		if(status == "Succeed"){
			//ˢ�µ�ǰ�û���Ϣ
			ServletActionContext.getRequest().getSession().setAttribute("cUser", userService.getUserInfo(cUser.getAccount()));
		}
		return status;
	}
	
	/**
	 * ��ص�Section��database�е�trigger�д���
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
