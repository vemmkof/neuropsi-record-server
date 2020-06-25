package com.ipn.escom.neuropsi.record.server.config;

import com.ipn.escom.neuropsi.commons.entity.values.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Component
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public CorsConfigurationSource getConfigurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource;
        urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*")); // dev && prod url
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.println(Role.ADMINISTRATOR.name());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/reset/password").permitAll()
                .antMatchers("/admin/**").hasAuthority(Role.ADMINISTRATOR.name())
                .antMatchers("/specialist/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.SPECIALIST.name())
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
