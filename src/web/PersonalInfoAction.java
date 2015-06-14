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
	MessageService messageService;
	Game game;
	String gameIDToDel;//ɾ����Ϸ��
	String cUserAccount;
	String rUserAccount;
	
	Message message;
	String receiveAccount;
	String sendAccount;
	
	
	 
	/**
	 * �鿴������Ϣʱ���ã�
	 * ��������Ϣ�浽session�ġ�rUser���У���ע���ղ�һ����rUser��Ա��
	 * �����ķ����浽session�ġ�rUserPosts���У���ʱ������
	 * �����Ļظ��浽session�ġ�rUserReplys���У�
	 * @return
	 */
	public String lookForOther(){
		String accountToLook = ServletActionContext.getRequest().getParameter("accountToLook");
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
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", accountToLook);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", accountToLook);
		ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
		ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		if(userService.isAdmin(accountToLook)){
			Guser cUser = (Guser)ServletActionContext.getRequest().getSession().getAttribute("cUser");
			if(cUser.getAccount().equals(accountToLook)){//����Ա�����Լ��ĸ������ģ���������Ϸװ�롰allGameList��
				gameInfoService.findAllGame();
			}
		}
		return "Succeed";
	}
	
	/**
	 * �ڸ�������ҳ�棬��ע��ť���ã����follow��
	 * @return
	 */
	public String followSomeone(){

		Guser cUser = userService.getUserInfo(cUserAccount);
		Guser rUser = userService.getUserInfo(rUserAccount);
		String status = followerService.followSomebody(cUser, rUser);
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", rUserAccount);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", rUserAccount);
		if(status.equals("Succeed")){
			//ˢ�µ�ǰ�û���Ϣ
			ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
			ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		}
		return status;
	}
	
	/**
	 * �ڸ�������ҳ�棬��ע��ť���ã�ȡ��follow��
	 * @return
	 */
	public String unFollowSomeone(){
		Guser cUser = userService.getUserInfo(cUserAccount);
		Guser rUser = userService.getUserInfo(rUserAccount);
		String status = followerService.unFollowSombody(cUser, rUser);
		
		List<Follow> WhoFollowMe = followerService.getFollow("guserByTargetId.account", rUserAccount);
		List<Follow> IFollowWho = followerService.getFollow("guserByFromId.account", rUserAccount);
		if(status.equals("Succeed")){
			//ˢ�µ�ǰ�û���Ϣ
			ServletActionContext.getRequest().getSession().setAttribute("WhoFollowMe", WhoFollowMe);
			ServletActionContext.getRequest().getSession().setAttribute("IFollowWho", IFollowWho);
		}
		return status;
	}
	
	/**
	 * ����Աɾ������ʱʹ��,ʹ��url����action
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
	 * admin��personalCenter�е��ã������Ϸ����ص�Section��database�е�trigger�д���<p>
	 * ʹ�ñ�form����action <p>
	 * gameInfoService�к�����δʵ��
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
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ǹ���Ա���޷����");
				return "Failed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "������¼");
			return "Failed";
		}
		
	}
	/**
	 * admin��personalCenter�е��ã�ɾ����Ϸ����ص�Section��database�е�trigger�д���
	 * @return
	 */
	public String delGame(){
		String status = gameInfoService.delGame(Integer.parseInt(gameIDToDel));
		//ˢ��allGameList
		gameInfoService.findAllGame();
		return status;
	}
	
	/**
	 * ������Ϣ�����и�����messagelist��ûд
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
			//������Ϣ�б�
			List<Message> list = messageService.getMessageAsSender(sendAccount);
			ServletActionContext.getRequest().getSession().setAttribute("SenMessageList", list);
			System.out.println("PersonalInfoAction.sendMessage() + sendlist.size : "+list.size() );
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�޴��û�������ȷ�Ϻ��ٷ�");
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
