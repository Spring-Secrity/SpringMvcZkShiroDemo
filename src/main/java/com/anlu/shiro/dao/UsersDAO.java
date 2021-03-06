/**
 * 
 */
package com.anlu.shiro.dao;

import com.anlu.shiro.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @desc org.chench.test.shiro.spring.dao.UsersDAO
 * @author chench9@lenovo.com
 * @date 2017年3月16日
 */
@Repository
public interface UsersDAO {
	
	public final String TABLE_ROLE_PERMISSIONS = "roles_permissions as rp "; // 角色权限表
	public final String TABLE_PERMISSIONS = "permissions as p "; // 权限表
	public final String TABLE_USER_ROLES = "user_roles "; // 用户角色表
	
//	@Select("select p.permission from " + TABLE_ROLE_PERMISSIONS +
//			" LEFT JOIN " + TABLE_PERMISSIONS +
//			" on (rp.perm_code = p.code) where role_name in " +
//			"(select role_name from " + TABLE_USER_ROLES + "where username = #{userName})")
//	public List<String> getUserPermissions(String username);

	@Select("select DISTINCT rp.permission from user_roles ur inner join roles_permissions rp " +
			"on rp.role_name = ur.role_name " +
			"where  ur.role_name in (select DISTINCT role_name from " +
			"user_roles where username = #{userName})")
	public List<String> getUserPermissions(String username);

	@Select("select DISTINCT r.role_name from user_roles r where r.username=#{userName}")
	public Set<String> getRolesByUsername(String username);


	@Select("SELECT * from users where username=#{userName}")
	public User findByUsername(String username);

	/**
	 * 添加用户角色
	 * @param userName
	 * @param roleName
	 * @return
	 */
	@Insert("insert into " + TABLE_USER_ROLES + "(username,role_name,create_time,update_time) values(#{userName},#{roleName},now(),now())")
	public Integer addRole(@Param("userName") String userName, @Param("roleName") String roleName);
	
	@Delete("delete from " + TABLE_USER_ROLES + " where username=#{userName} and role_name=#{roleName}")
	public Integer delRole(@Param("userName") String userName, @Param("roleName") String roleName);

}
