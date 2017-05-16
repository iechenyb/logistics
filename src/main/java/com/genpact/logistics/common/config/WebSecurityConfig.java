package com.genpact.logistics.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.genpact.logistics.modules.security.service.UserService;


@Configuration
//用于@PreAuthorize的生效,基于方法的权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends GlobalAuthenticationConfigurerAdapter {
	Log log = LogFactory.getLog(WebSecurityConfig.class);
	@Autowired
    private  UserService userService;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
    	log.info("WebSecurityConfig.init");
        auth.userDetailsService(userService);
    }

}
