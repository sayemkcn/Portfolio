package sayem.picoapps.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import sayem.picoapps.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
					.antMatchers("/register","/login", "/logout", "/", "/home","/portfolio","/portfolio/*","/portfolio/image/**", "/logout").permitAll()
					.antMatchers("/cv/**").hasAnyRole("ADMIN", "USER")
					.antMatchers("/portfolio/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error")
				.permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
		auth
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(new ShaPasswordEncoder(256));
	}
}
