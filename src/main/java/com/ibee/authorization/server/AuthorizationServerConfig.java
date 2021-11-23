package com.ibee.authorization.server;

import com.ibee.authorization.configs.BeansConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient(AuthorizationMessage.CLIENT_ID)
                    .secret(passwordEncoder.encode(AuthorizationMessage.CLIENT_SECRET))
                    .authorizedGrantTypes(AuthorizationMessage.PASSWORD_CREDENTIALS, AuthorizationMessage.REFRESH_TOKEN)
                    .scopes(AuthorizationMessage.SCOPE_WRITE, AuthorizationMessage.SCOPE_READ)
                    .accessTokenValiditySeconds(30)

                .and()
                    .withClient(AuthorizationMessage.CLIENT_ID_RS)
                    .secret(passwordEncoder.encode(AuthorizationMessage.CLIENT_SECRET_RS))

                .and()
                    .withClient(AuthorizationMessage.CLIENT_ID_API)
                    .secret(passwordEncoder.encode(AuthorizationMessage.CLIENT_SECRET_API))
                    .authorizedGrantTypes(AuthorizationMessage.CLIENT_CREDENTIALS)
                    .scopes(AuthorizationMessage.SCOPE_READ)

                .and()
                    .withClient(AuthorizationMessage.CLIENT_ID_FRONT)
                    .secret(passwordEncoder.encode(AuthorizationMessage.CLIENT_SECRET_FRONT))
                    .authorizedGrantTypes(AuthorizationMessage.AUTHORIZATION_CODE)
                    .scopes(AuthorizationMessage.SCOPE_WRITE, AuthorizationMessage.SCOPE_READ)
                    .redirectUris(AuthorizationMessage.REDIRECT_URI)

                .and()
                    .withClient(AuthorizationMessage.CLIENT_ID_IMPLICIT)
                    .authorizedGrantTypes(AuthorizationMessage.IMPLICIT)
                    .scopes(AuthorizationMessage.SCOPE_WRITE, AuthorizationMessage.SCOPE_READ)
                    .redirectUris(AuthorizationMessage.REDIRECT_URI_IMPLICIT);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(jwtAccessTokenConverter)
                .approvalStore(approvalStore(endpoints.getTokenStore()))
                .tokenGranter(tokenGranter(endpoints));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()");
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

    private ApprovalStore approvalStore(TokenStore tokenStore) {
        var approvalTokenStore = new TokenApprovalStore();
        approvalTokenStore.setTokenStore(tokenStore);
        return approvalTokenStore;
    }

}
