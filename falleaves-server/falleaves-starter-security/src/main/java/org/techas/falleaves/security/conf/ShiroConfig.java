package org.techas.falleaves.security.conf;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.techas.falleaves.security.realm.BasicRealm;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private int redisTimeout;

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/auth/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(basicRealm());
        defaultWebSecurityManager.setCacheManager(cacheManager());
        defaultWebSecurityManager.setSessionManager(sessionManager());

        return defaultWebSecurityManager;
    }

    @Bean
    public BasicRealm basicRealm() {
        return new BasicRealm();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        defaultSessionManager.setSessionDAO(sessionDAO());

        return defaultSessionManager;
    }

    @Bean
    public SessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("session");

        return redisSessionDAO;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(this.redisHost);
        redisManager.setPort(this.redisPort);
        redisManager.setPassword(this.redisPassword);
        redisManager.setExpire(1800);
        redisManager.setTimeout(this.redisTimeout);

        return redisManager;
    }

    @Bean
    public CacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix("cache:");

        return redisCacheManager;
    }

}