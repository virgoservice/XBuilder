package com.ramostear.xbuilder.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramostear.xbuilder.kit.PageDto;
import com.ramostear.xbuilder.model.User;
import com.ramostear.xbuilder.repository.UserRepository;
import com.ramostear.xbuilder.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordHelper passwordHelper;
	
	
	@Override
	public User add(User user) {
		passwordHelper.encryptPassword(user);
		user.setCreateTime(new Date());
		userRepository.create(user);
		return user;
	}

	@Override
	public User add(User user, Long... roleIds) {
		User u = this.add(user);
		userRepository.correlationRoles(u.getId(), roleIds);
		return u;
	}

	@Override
	public User update(User user) {
		if(user.getPassword() !=null && !"".equals(user.getPassword())) {
			passwordHelper.encryptPassword(user);
		}
		userRepository.update(user);
		return user;
	}

	@Override
	public User update(User user, Long... roleIds) {
		User u = userRepository.findOne(user.getId());
		if(u!=null){
			if(user.getPassword() != null && !"".equals(user.getPassword())){
				u.setPassword(user.getPassword());
				passwordHelper.encryptPassword(u);
			}
			if(user.getNickname() != null && !"".equals(user.getNickname())){
				u.setNickname(user.getNickname());
			}
			if(user.getSignature() != null && !"".equals(user.getSignature())){
				u.setSignature(user.getSignature());
			}
			if(user.getAvatar() != null && !"".equals(user.getAvatar())){
				u.setAvatar(user.getAvatar());
			}
			if(user.getEmail() != null && !"".equals(user.getEmail())){
				u.setEmail(user.getEmail());
			}
			if(user.getStatus() != null) {
				u.setStatus(user.getStatus());
			}
			if(user.getPhone() != null) {
				u.setPhone(user.getPhone());
			}
			if(user.getQq() != null) {
				u.setQq(user.getQq());
			}
			if(user.getWeibo() != null) {
				u.setWeibo(user.getWeibo());
			}
			userRepository.update(u);
			userRepository.uncorrelationAllRoles(u.getId());
			userRepository.correlationRoles(u.getId(), roleIds);
			return u;
		}else{
			return null;
		}
	}

	@Override
	public User update(User user, Map<String, Object> conditions) {
		if(user.getPassword() !=null && !"".equals(user.getPassword())) {
			passwordHelper.encryptPassword(user);
		}
		userRepository.updateWithCondition(user, conditions);
		return user;
	}

	@Override
	public void updatePassword(Long id, String newPassword) {
		User user = userRepository.findOne(id);
		if(user != null) {
			user.setPassword(newPassword);
			passwordHelper.encryptPassword(user);
			userRepository.update(user);
		}
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteUserRole(id);
		userRepository.delete(id);
	}

	@Override
	public void batchDelete(Long... ids) {
		userRepository.batchDeleteUserRole(ids);
		userRepository.batchDelete(ids);
	}

	@Override
	public void correlationRoles(Long id, Long... roleIds) {
		userRepository.correlationRoles(id, roleIds);
	}

	@Override
	public void uncorrelationAllRoles(Long id) {
		userRepository.uncorrelationAllRoles(id);
	}

	@Override
	public List<String> findRoles(String username) {
		return userRepository.findRoleNames(username);
	}

	@Override
	public List<String> findPermissions(String username) {
		return userRepository.findPermissions(username);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<Long> findRoleById(Long id) {
		return userRepository.findRoleIds(id);
	}

	@Override
	public Long size() {
		return userRepository.size();
	}

	@Override
	public Long size(Map<String, Object> conditions) {
		if(conditions.isEmpty()) {
			conditions = new HashMap<String,Object>();
		}
		return userRepository.sizeWithConditions(conditions);
	}

	@Override
	public PageDto<User> pagination(int offset, int size, String orderParam, boolean orderType,
			Map<String, Object> conditions) {
		if(conditions.isEmpty()) {
			conditions = new HashMap<String,Object>();
		}
		List<User> list = userRepository.pagination(offset, size, orderParam, orderType, conditions);
		Long totalSize = userRepository.sizeWithConditions(conditions);
		return new PageDto<User>(totalSize, offset, size, list);
	}

}
