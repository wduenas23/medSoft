package com.wecode.medsoft.process;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wecode.medsoft.contracts.authorization.AuthorizationDetail;
import com.wecode.medsoft.contracts.authorization.UserDetail;
import com.wecode.medsoft.entities.Role;
import com.wecode.medsoft.entities.RoleAuthorization;
import com.wecode.medsoft.entities.User;
import com.wecode.medsoft.persistence.RoleAuthorizationRepository;
import com.wecode.medsoft.persistence.UserRepositoryCustom;
import com.wecode.medsoft.security.ShaEncryption;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorizationProcess {

	
	private RoleAuthorizationRepository roleAuthorizationRepository;
	private UserRepositoryCustom userRepository ;
	
	public AuthorizationProcess(RoleAuthorizationRepository roleAuthorizationRepository,UserRepositoryCustom userRepository) {
		this.roleAuthorizationRepository=roleAuthorizationRepository;
		this.userRepository=userRepository;
	}
	
	
	public ResponseEntity<UserDetail> login(UserDetail userInfo) {
		try {
			UserDetail ud=new UserDetail();
			ShaEncryption sha=new ShaEncryption();
			String encryptedPassword=ShaEncryption.encrypt(userInfo.getPassword());
			String decryptedPassword=ShaEncryption.decrypt(encryptedPassword);
			/*log.error(encryptedPassword);
			log.error(decryptedPassword);*/
			User user=userRepository.findUser(userInfo.getUser());
			if(user!=null) {
				String decryptedPass=ShaEncryption.decrypt(user.getUsrPassword());
				if(decryptedPass.equals(userInfo.getPassword())) {
					ud.setUser(user.getUsrUser());
					ud.setRoleId(user.getRole().getRoleId());
					ud.setUserName(user.getUsrName());
					return new ResponseEntity<>(ud,HttpStatus.OK);
				}else
					return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
				
			}
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		} catch(NoResultException nre) { 
			log.error("Usuario no encontrado",nre.getMessage());
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			log.error("error getting authorizations by role",e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<AuthorizationDetail>> getAuthorizationByRole(Integer roleId) {
		try {
			List<AuthorizationDetail> parents=new ArrayList<>();
			AuthorizationDetail parent=null;
			
			Role role=new Role();
			role.setRoleId(roleId);
			List<RoleAuthorization> roleAuthList= roleAuthorizationRepository.findByRole(role);
			
			//BUILDING PARENT MENU
			for (RoleAuthorization roleAuthorization : roleAuthList) {
				if(roleAuthorization.getAuthorization().getAuthParent()) {
					parent=new AuthorizationDetail();
					parent.setDisplayName(roleAuthorization.getAuthorization().getAuthDisplayName());
					parent.setIcon(roleAuthorization.getAuthorization().getAuthIcon());
					parent.setOrder(roleAuthorization.getAuthorization().getAuthOrder());
					parent.setParent(roleAuthorization.getAuthorization().getAuthParent());
					parent.setId(roleAuthorization.getAuthorization().getAuthId());
					parents.add(parent);
				}
			}
			
			for (AuthorizationDetail roleAuthorization : parents) {
				roleAuthorization.setChilds(buildChilds(roleAuthorization,roleAuthList));
			}
			
			return new ResponseEntity<>(parents,HttpStatus.OK);
		} catch (Exception e) {
			log.error("error getting authorizations by role",e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private List<AuthorizationDetail> buildChilds(AuthorizationDetail roleAuthorization, List<RoleAuthorization> roleAuthList) {
		List<AuthorizationDetail> childs=new ArrayList<>();
		AuthorizationDetail child=new AuthorizationDetail();
		
		for (RoleAuthorization auth : roleAuthList) {
			if(!auth.getAuthorization().getAuthParent() && auth.getAuthorization().getAuthParentId().intValue() == roleAuthorization.getId().intValue()) {
				child=new AuthorizationDetail();
				child.setDisplayName(auth.getAuthorization().getAuthDisplayName());
				child.setIcon(auth.getAuthorization().getAuthIcon());
				child.setOrder(auth.getAuthorization().getAuthOrder());
				child.setParent(auth.getAuthorization().getAuthParent());
				child.setId(auth.getAuthorization().getAuthId());
				child.setLink(auth.getAuthorization().getAuthLink());
				childs.add(child);
			}
		}
		
		return childs;
	}
	
}
