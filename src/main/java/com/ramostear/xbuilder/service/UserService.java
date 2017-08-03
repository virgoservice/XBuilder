package com.ramostear.xbuilder.service;

import java.util.List;
import java.util.Map;

import com.ramostear.xbuilder.kit.PageDto;
import com.ramostear.xbuilder.model.User;

public interface UserService {

	public User add(User user);
	
	public User add(User user,Long...roleIds);
	
	public User update(User user);
	
	public User update(User user,Long...roleIds);
	
	public User update(User user,Map<String, Object>conditions);
	
	public void updatePassword(Long id,String newPassword);
	
	public User findOne(Long id);
	
	public void delete(Long id);
	
	public void batchDelete(Long...ids);
	
	public void correlationRoles(Long id,Long...roleIds);
	
	public void uncorrelationAllRoles(Long id);
	
	public List<String> findRoles(String username);
	
	public List<String> findPermissions(String username);
	
	public User findByName(String name);
	
	public List<Long> findRoleById(Long id);
	
	public Long size();
	
	public Long size(Map<String, Object>conditions);
	
	public PageDto<User> pagination(int offset,int size,String orderParam,boolean orderType,Map<String,Object>conditions);
	
}
