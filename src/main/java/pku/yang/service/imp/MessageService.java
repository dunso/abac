package pku.yang.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.IMessageDao;
import pku.yang.dao.imp.MessageDao;
import pku.yang.model.Message;
import pku.yang.service.IMessageService;

@Service
public class MessageService implements IMessageService{
	
	@Autowired
	private IMessageDao messagedao; 

	@Override
	public void save_mess(Message mess) {
		// TODO Auto-generated method stub
		messagedao.save(mess);
		
	}
	
	@Override
	public void save_mess_state(String mess_id, String state) {
		// TODO Auto-generated method stub
		messagedao.save_state(mess_id, state);
		
	}

	@Override
	public void add_mess(String user_id, String fg_id, String state) {
		// TODO Auto-generated method stub
		Message mess=new Message();
//		mess.setMess_id(mess_id);
		mess.setUser_id(user_id);
		mess.setFg_id(fg_id);
		mess.setState(state);
		this.save_mess(mess);

	}

	@Override
	public void delete_mess(String mess_id) {
		// TODO Auto-generated method stub
		messagedao.delete(mess_id);
		
	}

	@Override
	public Message search_mess_info(String mess_id) {
		// TODO Auto-generated method stub
		Message mess=messagedao.getByID(mess_id);
		return mess;
	}
	

	@Override
	public List<Message> mess_list() {
		// TODO Auto-generated method stub
	
		List<Message> list_mess=messagedao.getList("from Message");
		return list_mess;
	}

	@Override
	public List<Message> search_mess_byuser(String user_id) {
		// TODO Auto-generated method stub
		List<Message> list_usermess=messagedao.userMessofNOrespond(user_id);
		return list_usermess;
	}

	@Override
	public List<Message> search_user_fg(String user_id) {
		// TODO Auto-generated method stub
		List<Message> list_user_fg=messagedao.userMessResponded(user_id);
		return list_user_fg;
	}

	@Override
	public List<Message> search_mess_byfg(String fg_id) {
		// TODO Auto-generated method stub
		List<Message> list_fgmess=messagedao.mess_of_fg(fg_id);
		return list_fgmess;
	}




}
