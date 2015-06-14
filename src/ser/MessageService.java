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
	 * 加载聊天页面时使用,返回历史消息，最多50条（从DAO类中写特殊查询）
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
	 * 应该被计时器调用，以实现刷新信息的效果，可以返回有新消息列表
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
