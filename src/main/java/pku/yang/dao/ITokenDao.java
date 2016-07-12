package pku.yang.dao;

import pku.yang.model.Token;

public interface ITokenDao {

	void save(Token token);
	void delete(String token_id);
	Token getByID(String token_id);
	
}
