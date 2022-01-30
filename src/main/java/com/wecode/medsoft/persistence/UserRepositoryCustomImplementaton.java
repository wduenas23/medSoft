package com.wecode.medsoft.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.wecode.medsoft.entities.User;

@Service
public class UserRepositoryCustomImplementaton implements UserRepositoryCustom{

	
	@PersistenceContext // or even @Autowired
    private EntityManager entityManager;
	
	@Override
	public User findUser(String usrUser) {
		String sql=null;
		try {
			sql=" SELECT u FROM User u WHERE u.usrUser = :user";
			Query q=this.entityManager.createQuery(sql);
			q.setParameter("user", usrUser);
			//q.setParameter("password", usrPassword);
			return  (User) q.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}

}
