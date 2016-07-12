package pku.yang.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.ITokenDao;
import pku.yang.model.Token;
import pku.yang.service.ITokenService;

@Service
public class TokenService implements ITokenService {

	@Autowired
	private ITokenDao tokendao;

	@Override
	public Token findTokenInfo(String Token_id) {
		// TODO Auto-generated method stub
		return	tokendao.getByID(Token_id);
	}

	@Override
	public void save(Token token) {
		// TODO Auto-generated method stub
		tokendao.save(token);
		
	}

	@Override
	public void deleteToken(String token_id) {
		// TODO Auto-generated method stub
		tokendao.delete(token_id);
	}

	
}
