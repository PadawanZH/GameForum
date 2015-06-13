package web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.Section;
import ser.PostService;
import ser.ReplyService;

public class PostDetailAction {
	PostService postService;
	ReplyService replyService;
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
}
