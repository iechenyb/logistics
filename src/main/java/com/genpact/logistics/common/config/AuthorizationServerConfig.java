package com.genpact.logistics.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.genpact.logistics.modules.security.service.UserService;

/**
 * 授权服务器配置类
 * 它主要负责授权服务器的配置
 * 包括：信任的客户端信息的管理、请求令牌的 URL 的配置、令牌的管理、如何认证用户的配置、对于请求令牌的 URL 的安全约束的配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	Log log = LogFactory.getLog(AuthorizationServerConfig.class);
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")//授权客户端信息 
                .secret("m_volunteer")//授权客户端信息 
                .authorizedGrantTypes("password", "refresh_token")            
                .scopes("read", "write")
                .accessTokenValiditySeconds(2592000);
        clients.inMemory()
        .withClient("sina")//授权客户端信息 
        .secret("1")//授权客户端信息 
        .authorizedGrantTypes("authorization_code")            
        .scopes("app")
        .accessTokenValiditySeconds(2592000);
        log.info("初始化授权信息！");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userService);
        log.info("配置userSerivce！");
    }

    @Bean
    public TokenStore tokenStore() {
    	 log.info("tokenStore！");
        return new InMemoryTokenStore();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
    	log.info("tokenServices！");
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true); // support refresh token
        tokenServices.setTokenStore(tokenStore); // use in-memory token store
        return tokenServices;
    }
}
