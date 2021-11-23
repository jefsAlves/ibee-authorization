package com.ibee.authorization.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class BeansConfiguration {

    @Value("${ibee.jwt.keystore.path}")
    private ClassPathResource resourceJks;

    @Value("${ibee.jwt.keystore.password}")
    private String passwordResourceJks;

    @Value("${ibee.jwt.keystore.alias}")
    private String aliasKeyPair;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        var path = resourceJks;
        var password = passwordResourceJks;
        var alias = aliasKeyPair;

        var file = new KeyStoreKeyFactory(path, password.toCharArray());
        var open = file.getKeyPair(alias);
        jwtAccessTokenConverter.setKeyPair(open);

        return jwtAccessTokenConverter;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
