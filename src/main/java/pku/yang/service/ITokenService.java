package pku.yang.service;

import pku.yang.model.Token;

public interface ITokenService {


	Token findTokenInfo(String Token_id);
	void save(Token token);
	void deleteToken(String token_id);
	
}
