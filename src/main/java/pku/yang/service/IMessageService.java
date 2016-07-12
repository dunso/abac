package pku.yang.service;

import java.util.List;

import pku.yang.model.Message;

public interface IMessageService {
	
	void save_mess(Message mess);
	void save_mess_state(String mess_id,String state);
	void add_mess(String user_id,String fg_id,String state);
	void delete_mess(String mess_id);
	Message search_mess_info(String mess_id);
	List<Message> search_mess_byuser(String user_id);
	List<Message> search_user_fg(String user_id);
	List<Message> mess_list();
	List<Message> search_mess_byfg(String fg_id);

}
