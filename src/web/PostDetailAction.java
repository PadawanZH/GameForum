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
		Post newCurPost = postService.getPostByID(Integer.parseInt(favouritePostID));
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = postService.markFavourite(newCurPost, cUser);
		
		if(status == "Succeed"){
			//����curPost�Ը�����curPost�е�Favourites��Ϣ��ע�⣬��favouritePostID���ǺϷ�ֵ����status=failed���������δ���Σ����ص�������curPostΪnull
			ServletActionContext.getRequest().getSession().setAttribute("curPost", newCurPost);
			ServletActionContext.getRequest().getSession().setAttribute("cUser", cUser);
		}
		return status;
	}
	
	/**
	 * postdetailҳ��ȡ���ղع���,������session��curPost��Ϣ
	 * @return
	 */
	public String unFavouritePost(){
		String favouritePostID = ServletActionContext.getRequest().getParameter("favouritePostID");
		String cUserAccount = ServletActionContext.getRequest().getParameter("cUserAccount");
		Post newCurPost = postService.getPostByID(Integer.parseInt(favouritePostID));
		Guser cUser = userService.getUserInfo(cUserAccount);
		
		String status = postService.unMarkFavourite(Integer.parseInt(favouritePostID), cUser);
		if(status == "Succeed"){
			//����curPost�Ը�����curPost�е�Favourites��Ϣ��ע�⣬��favouritePostID���ǺϷ�ֵ����status=failed���������δ���Σ����ص�������curPostΪnull
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
