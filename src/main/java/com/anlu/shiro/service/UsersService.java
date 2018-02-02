/**
 * 
 */
package com.anlu.shiro.service;

import com.anlu.shiro.bean.User;
import com.anlu.shiro.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @desc org.chench.test.shiro.spring.service.UsersService
 * @author chench9@lenovo.com
 * @date 2017年3月16日
 */
@Service
public class UsersService {
	@Autowired
	private UsersDAO usersDAO;
	
	/**
	 * 获取用户权限
	 * @param username
	 * @return
	 */
	public List<String> getUserPermissions(String username) {
		return usersDAO.getUserPermissions(username);
	}
	
	public Set<String> getUserPermissionSet(String username) {
		List<String> permissionList = usersDAO.getUserPermissions(username);
		Set<String> permissionSet = new HashSet<String>();
		permissionSet.addAll(permissionList);
		return permissionSet;
	}

	public Set<String> getRolesByUsername(String username){
		return usersDAO.getRolesByUsername(username);
	}

	public User findByUsername(String username){
      return usersDAO.findByUsername(username);
	}
	
	public Integer addRole(String userName, String roleName) {
		return usersDAO.addRole(userName, roleName);
	}
	
	public Integer delRole(String userName, String roleName) {
		return usersDAO.delRole(userName, roleName);
	}

}
