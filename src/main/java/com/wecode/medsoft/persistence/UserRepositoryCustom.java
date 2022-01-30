package com.wecode.medsoft.persistence;

import com.wecode.medsoft.entities.User;



public interface UserRepositoryCustom {

	User findUser(String usrUser);
}
