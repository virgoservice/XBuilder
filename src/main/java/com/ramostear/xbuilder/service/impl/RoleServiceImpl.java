package com.ramostear.xbuilder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramostear.xbuilder.kit.PageDto;
import com.ramostear.xbuilder.model.Role;
import com.ramostear.xbuilder.repository.RoleRepository;
import com.ramostear.xbuilder.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void add(Role role) {
		roleRepository.create(role);
	}

	@Override
	public void add(Role role, Long... pids) {
		roleRepository.create(role);
		roleRepository.correlationRolePermission(role.getId(), pids);
	}

	@Override
	public void update(Role role) {
		roleRepository.update(role);
	}

	@Override
	public void update(Role role, Long... pids) {
		roleRepository.update(role);
		roleRepository.uncorrelationOneRolePermission(role.getId());
		roleRepository.correlationRolePermission(role.getId(), pids);
	}

	@Override
	public void delete(Long id) {
		roleRepository.uncorrelationOneRolePermission(id);
		roleRepository.delete(id);
	}

	@Override
	public void batchDelete(Long... ids) {
		roleRepository.uncorrelationManyRolePermission(ids);
		roleRepository.batchDelete(ids);
	}

	@Override
	public Role findOne(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> findAll(String orderParam, boolean orderType, Map<String, Object> conditions) {
		if(conditions.isEmpty()) {
			conditions = new HashMap<String,Object>();
		}
		return roleRepository.findAll(orderParam, orderType, conditions);
	}

	@Override
	public List<Long> findPermission(Long id) {
		return roleRepository.findPermission(id);
	}

	@Override
	public Long size() {
		return roleRepository.size();
	}

	@Override
	public Long sizeWithConditions(Map<String, Object> conditions) {
		if(conditions.isEmpty()) {
			conditions = new HashMap<String,Object>();
		}
		return roleRepository.sizeWithConditions(conditions);
	}

	@Override
	public PageDto<Role> pagination(int offset, int size, String orderParam, boolean orderType,
			Map<String, Object> conditions) {
		if(conditions.isEmpty()) {
			conditions = new HashMap<String,Object>();
		}
		List<Role> list = roleRepository.pagination(offset, size, orderParam, orderType, conditions);
		Long totalSize = roleRepository.sizeWithConditions(conditions);
		return new PageDto<Role>(totalSize, offset, size, list);
	}

}
