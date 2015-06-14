package ser;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.ReplyDAO;

public class ReplyService {
	ReplyDAO replyDAO;
	PostService postService;
	UserService userService;
	
	/**
	 * �õ�post�Ļظ�������ʱ����������,һ����postҳ���ʼ��ʱʹ�ã���������ظ���
	 * @param post
	 * @return
	 */
	public List<Reply> getReplyInPostbyTime(Post post){
		List<Reply> list;
		try{
			list = replyDAO.findByPostOrderByReplyTime(post);
			//������һ�½�֮��service�����session�������Ƿ��ظ�action
			return list;
		}catch (RuntimeException re) {
			System.out.println("attr name is wrong in ReplyService.getReplyInPost.findByAOrderByB");
		}
		return null;
	}
	
	/**
	 * �����ض�User��Post��ʷ�����ڸ������ģ�
	 * @return
	 */
	public List<Reply> getPostForUserOrderByPostTime(Guser author){
		return replyDAO.findByUserOrderByReplyTime(author);
	}
	
	/**
	 * ��ӻظ�����Ҫ�û����͵�ǰ���Ӷ���
	 * @return
	 */
	public String addReply(Guser cUser, Post curPost, String contents){
		Reply reply = new Reply();
		if(cUser != null && curPost != null){
			reply.setGuser(cUser);
			reply.setPost(curPost);
			
			reply.setFloor(0);
			reply.setFavouriteNum(0);
			reply.setPostTime(new Timestamp(System.currentTimeMillis()));
			reply.setContents(contents);
			replyDAO.save(reply);
			return "Succeed";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "����ʧ�ܣ���������Ϊ��û�е�¼���뷵�ص�¼������");
			return "Failed";
		}
		
	}
	
	/**
	 *	ɾ��reply������ֻ�й���Ա����ɾ��������Ҫ���Ȩ�ޣ�����������
	 * @param reply
	 * @return
	 */
	public String delReply(String account,Integer delReplyID){
		if(userService.isAdmin(account)){
			Reply reply = replyDAO.findById(delReplyID);
			if(reply == null){
				ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ѱ�ɾ������ˢ�����ԣ��򷵻���ҳ��");
				return "Failed";
			}else{
				replyDAO.delete(reply);
				return "Succeed";
			}
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("ErrorInfo", "�����ǹ���Ա��лл");
			return "Failed";
		}
	}
	
	/**
	 * ���ɾ��ʱ��Ȩ���Ƿ����ɾ��
	 * @return
	 */
	private boolean checkDeletePermission(){
		return false;
	}

	public ReplyDAO getReplyDAO() {
		return replyDAO;
	}


	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}


	public PostService getPostService() {
		return postService;
	}


	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
