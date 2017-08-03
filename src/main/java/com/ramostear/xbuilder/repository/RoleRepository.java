package com.ramostear.xbuilder.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ramostear.xbuilder.model.Role;

@Mapper
public interface RoleRepository {

	public void create(Role role);
	
	public void update(Role role);
	
	public void delete(Long id);
	
	public Role findOne(Long id);
	
	public List<Role> findAll(String orderParam,boolean orderType,Map<String, Object>conditions);
	
	public Long size();
	
	public Long sizeWithConditions(Map<String,Object>conditions);
	
	public List<Role> pagination(int offset,int size,String orderParam,boolean orderType,Map<String, Object>conditions);
	
	public void batchDelete(Long...ids);
	
	public void uncorrelationOneRoleUser(Long id);
	
	public void uncorrelationManyRoleUser(Long...ids);
	
	public void uncorrelationOneRolePermission(Long id);
	
	public void uncorrelationManyRolePermission(Long...ids);
	
	public void correlationRolePermission(Long id,Long...pids);
	
	public List<Long> findPermission(Long id);
	
}
