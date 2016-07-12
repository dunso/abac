package pku.yang.dao;

import java.util.List;

import pku.yang.model.Message;

public interface IMessageDao {
	
	void save(Message mess);
	void save_state(String mess_id,String state);
	void delete(String mess_id);
	Message getByID(String mess_id);
	List<Message> getList(String hql);
	List<Message> userMessofNOrespond(String user_id);
	List<Message> userMessResponded(String user_id);
	List<Message> mess_of_fg(String fg_id);
	
}
