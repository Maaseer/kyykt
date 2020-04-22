package com.zstu.ky.kyykt.zuul.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final
    AuthenticationManager authenticationManager;

    private final
    RedisConnectionFactory redisConnectionFactory;

    private final UserDetailsService userDetailsService;


    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("test_client")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(604800)
                .resourceIds("rid")
                .scopes("service")
                .secret("$2a$10$XF.sRp3GsR4jL7KUWYl0qe.j/ctC41mnrnxOWB0BLbE/I1KjOizAi");
    }

    @Bean
    public TokenStore getTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(getTokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
