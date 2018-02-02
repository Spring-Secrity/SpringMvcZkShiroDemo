package com.anlu.shiro.dao;

import com.anlu.shiro.bean.User;
import com.anlu.shiro.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
    @Autowired
    private UsersService userService ;


    /**
     * 提供用户信息返回权限信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询当前用户拥有的角色
       //获取当前用户的所有权限
        Set<String> permissions = userService.getUserPermissionSet(username);

        //获取当前用户的所有角色
        Set<String> roles = userService.getRolesByUsername(username);

        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
//        if (user.getLocked() == 0) {
//            // 用户被管理员锁定抛出异常
//            throw new LockedAccountException();
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(), ByteSource.Util.bytes("123"), getName());

        return authenticationInfo;
    }
}
