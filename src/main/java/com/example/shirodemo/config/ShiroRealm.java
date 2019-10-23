package com.example.shirodemo.config;

import java.util.HashSet;
import java.util.Set;

import com.example.shirodemo.bean.User;
import com.example.shirodemo.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-22 09:48:44
 **/

//继承AuthorizingRealm，重写认证和授权方法
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 授权方法，如果不设置缓存管理的话，需要访问需要一定的权限或角色的请求时会进入这个方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User principal = (User) principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if("admin".equals(principal.getUsername())){
            roles.add("admin");
        }

        return new SimpleAuthorizationInfo(roles);
    }

    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        //根据登录名查询用户，这里不用校验密码，因为密码的校验是交给shiro来完成的
        User userInfo=userDao.findByUserName(userToken.getUsername());

        if(userInfo == null) {
            throw new IncorrectCredentialsException("用户名或密码不正确");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,//用户
                userInfo.getPassword(),//密码
                ByteSource.Util.bytes(userInfo.getUsername()),//盐值用 ByteSource.Util.bytes 来生成
                getName()//realm name
        );
        return authenticationInfo;
    }
    public static void main(String[] args) {
        //算出盐值
        String credentials="123";
        String salt="小苏";
        String hashAlgorithmName="MD5";
        int hashIterations=1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);

    }

}
