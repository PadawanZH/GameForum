package ser;

import java.util.List;

import dao.Guser;
import dao.Message;
import dao.MessageDAO;

public class MessageService {
	MessageDAO messageDAO;
	
	public String SendMessage(Message message){
		messageDAO.save(message);
		return "Succeed";
	}
	/**
	 * ��������ҳ��ʱʹ��,������ʷ��Ϣ�����50������DAO����д�����ѯ��
	 * @return
	 */
	public List<Message> getMessageFromFriend(){
		return null;
	}
	
	public List<Message> getMessageAsReceiver(String account){
		return messageDAO.findByReceiver(account);
	}
	
	public List<Message> getMessageAsSender(String account){
		return messageDAO.findBySender(account);
	}
	
	/**
	 * Ӧ�ñ���ʱ�����ã���ʵ��ˢ����Ϣ��Ч�������Է���������Ϣ�б�
	 * @return
	 */
	public List<Message> RefreshMessage(){
		return null;
	}
	
	public String MarkAsReaded(){
		return null;
	}
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

}
