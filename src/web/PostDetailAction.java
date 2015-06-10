package web;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import dao.Post;
import dao.Reply;
import ser.PostService;
import ser.ReplyService;

public class PostDetailAction {
	PostService postService;
	ReplyService replyService;
	
	/**
	 * ��ǰ��GamePage����url����postID����curPost����session����post��reply����session
	 * @return
	 */
	public String getInPostDetail(){
		String chosedPostID = ServletActionContext.getRequest().getParameter("postID");
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
}
