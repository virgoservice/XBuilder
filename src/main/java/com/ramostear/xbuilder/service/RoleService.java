package com.ramostear.xbuilder.service;

import java.util.List;
import java.util.Map;

import com.ramostear.xbuilder.kit.PageDto;
import com.ramostear.xbuilder.model.Role;

public interface RoleService {
	
	public void add(Role role);

	public void add(Role role,Long...pids);
	
	public void update(Role role);
	
	public void update(Role role,Long...pids);
	
	public void delete(Long id);
	
	public void batchDelete(Long...ids);

	public Role findOne(Long id);
	
	public List<Role> findAll(String orderParam,boolean orderType,Map<String, Object>conditions);
	
	public List<Long> findPermission(Long id);
	
	public Long size();
	
	public Long sizeWithConditions(Map<String,Object>conditions);
	
	public PageDto<Role> pagination(int offset,int size,String orderParam,boolean orderType,Map<String, Object>conditions);

	
}
