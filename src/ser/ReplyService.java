package ser;

import java.util.List;

import dao.Guser;
import dao.Post;
import dao.Reply;
import dao.ReplyDAO;

public class ReplyService {
	ReplyDAO replyDAO;
	
	/**
	 * �õ�post�Ļظ�������ʱ������
	 * @param post
	 * @return
	 */
	public List<Reply> getReplyInPost(Post post){
		return null;
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
	
	
}
