package com.emsi.parking.config;


import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emsi.parking.security.AuthEntryPointJwt;
import com.emsi.parking.security.AuthTokenFilter;
import com.emsi.parking.service.impl.AuthenticationProviderService;
import com.emsi.parking.service.impl.JpaUtilisateurDetailsService;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter{
@Autowired
 private AuthenticationProviderService authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
            "/api/auth/**"
    };
    
@Autowired
	JpaUtilisateurDetailsService userDetailsService;
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
        
        @Override
public void configure(WebSecurity web) {
    web.ignoring()
            .antMatchers("/api/**", // Swagger UI HTML and resources
                    "/v3/api-docs/**", // OpenAPI v3 API-docs
                    "/swagger-ui/**", // Swagger UI webjars
                    "/swagger-ui.html" // Swagger UI index page (HTML)
            );
}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
           
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint) unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
			//.antMatchers("/api/test/**").permitAll()


                //.antMatchers("/v1/api//**/ /").permitAll()
                .antMatchers("/api/auth/signin").permitAll()
                .antMatchers("/api/auth/signup").permitAll()
                .antMatchers("/swagger-ui/").permitAll()  
                .antMatchers("/swagger-ui.html").permitAll()  
                .antMatchers("/v3/api-docs/**").permitAll()  
              //  .antMatchers("/api/admin/auth/signup").permitAll()
               // .antMatchers("/api/admin/auth/signin").permitAll()
                //.antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
              //  .antMatchers("/api/auth/**").hasAuthority("ROLE_USER")
                .antMatchers("/**").permitAll() //permit all the routers after swagger-ui.html
			
                .anyRequest().authenticated();
                        
		http.addFilterBefore((Filter) authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
              
	}
}
