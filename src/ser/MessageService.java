package ser;

import java.util.List;

import dao.Message;
import dao.MessageDAO;

public class MessageService {
	MessageDAO messageDAO;
	
	public String SendMessage(){
		return null;
	}
	/**
	 * 加载聊天页面时使用,返回历史消息，最多50条（从DAO类中写特殊查询）
	 * @return
	 */
	public List<Message> getMessageFromFriend(){
		return null;
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

}
