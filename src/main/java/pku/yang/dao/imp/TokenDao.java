package pku.yang.dao.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.model.Token;
import pku.yang.dao.ITokenDao;


@Repository
public class TokenDao implements ITokenDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Token token ) {
		hibernateTemplate.saveOrUpdate(token);
	}
	
	@Override
	public void delete(String token_id){
		
		hibernateTemplate.delete(hibernateTemplate.get(Token.class, token_id));

	}
	
	@Override
	public Token getByID(String token_id){
		
		Token token = (Token)hibernateTemplate.get(Token.class, token_id);
		return token;
		
	}
}
