package com.ramostear.xbuilder.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ramostear.xbuilder.model.User;
/**
 * 
 * @author Ramostear
 *
 */
@Mapper
public interface UserRepository {
	
	public void create(User user);
	
	public void delete(Long id);
	
	public void update(User user);
	
	public void updateWithCondition(User user,Map<String,Object>conditions);

	public User findOne(Long id);
	
	public User findByName(String username);
	
	public List<User> findAll(@Param("orderParam")String orderParam,@Param("orderType")boolean orderType,Map<String,Object>conditions);
	
	public Long size();
	
	public Long sizeWithConditions(Map<String,Object>conditions);
	
	public List<User> pagination(int offset,int size,String orderParam,boolean orderType,Map<String,Object>conditions);
	
	public void batchDelete(Long...ids);
	
	public void correlationRoles(Long id,Long...rids);
	
	public void uncorrelationRoles(Long id,Long...rids);
	
	public void uncorrelationAllRoles(Long id);

	public void deleteUserRole(Long id);
	
	public void batchDeleteUserRole(Long...ids);
	
	public List<String> findRoleNames(String username);
	
	public List<String> findPermissions(String username);
	
	public List<Long> findRoleIds(Long id);
	
}
