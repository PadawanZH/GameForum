package ser;

import java.util.List;
import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.ReplyDAO;

public class ReplyService {
	ReplyDAO replyDAO;
	PostService postService;
	
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
	 * ��ӻظ�
	 * @return
	 */
	public String addReply(Guser cUser, Reply reply){
		return null;
	}
	
	/**
	 *	ɾ��reply������ֻ�а������ظ��ߡ�����Ա����ɾ��������Ҫ���Ȩ�ޣ�����������
	 * @param reply
	 * @return
	 */
	public String deleteReply(Guser cUser, Reply reply){
		return null;
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
	
	
}
