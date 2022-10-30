package br.com.fiserv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
		
	JwtAuthenticationConverter getJwtAuthenticationConverter() {
    	JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
    	converter.setAuthoritiesClaimName("authorities");
    	converter.setAuthorityPrefix("");
    	JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
    	authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
    	return authenticationConverter;
	}

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.cors()
        .and()
          .authorizeRequests()
            .antMatchers("/api/employee/**")
              .hasAuthority("admin")
            .antMatchers("/swagger.html/**")
              .permitAll()
            .antMatchers("/api/**")
              .authenticated()
        .and()
          .oauth2ResourceServer()
            .jwt()
              .jwtAuthenticationConverter(getJwtAuthenticationConverter());

        return http.build();
    }
   
}
