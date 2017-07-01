package com.twinsoft.security;

import java.security.KeyPair;
import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


/**
 * @author Miodrag Pavkovic
 */
@Configuration
@EnableAuthorizationServer
public class AppOAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter { 
	@Inject
    private AuthenticationManager authenticationManager;

    @Value("${spring.security.client.id}")
    private String clientId;
    @Value("${spring.security.client.secret}")
    private String clientSecret;
    @Value("${spring.security.client.scope}")
    private String clientScope;
    @Value("${spring.security.client.grants}")
    private String[] grants;
   /* @Value("${phouse.security.keystore.file}")
    private String keystoreFile;
    @Value("${phouse.security.keystore.alias}")
    private String keystoreAlias;
    @Value("${phouse.security.keystore.storepass}")
    private String storepass;
    @Value("${phouse.security.keystore.keypass}")
    private String keypass;*/

 /*   @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }*/

   /* @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        final KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(keystoreFile), storepass.toCharArray())
            .getKeyPair(keystoreAlias, keypass.toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyPair);

        return jwtAccessTokenConverter;
    }
*/
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grants)
                .scopes(clientScope)
                .autoApprove(true);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
           // .tokenStore(tokenStore())
            //.accessTokenConverter(jwtAccessTokenConverter())
            .authenticationManager(authenticationManager);
    }
}
