package io.security.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

	/**
	 * spring 설정파일보다 config 파일이 우선이기때문에 아래 설정값이 적용됨
	 * 메모리에 정보 저장
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("user1")
			.password("{noop}1111")
			.roles("USER")
			.build();
		UserDetails user2 = User.withUsername("user2")
			.password("{noop}1111")
			.roles("USER")
			.build();
		UserDetails user3 = User.withUsername("user3")
			.password("{noop}1111")
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}

}
