package com.softib.loanmanager.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;






@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService userDetailsService ;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        .antMatchers(
        			"/v2/api-docs",
        			"/configuration/**",
        			"/swagger*/**",
        			"/swagger-resources/**",
        			"/configuration/security",
        			"/swagger-ui.html",
        			"/webjars/**").permitAll()
        //.and().authorizeRequests().antMatchers("/*").hasAnyAuthority("ADMIN","BANKER","USER")
        .and().authorizeRequests().antMatchers("POST", "/loanTypes"). hasAuthority("ADMIN")
        .and().authorizeRequests().antMatchers("DELETE", "/loanTypes/{id}"). hasAuthority("ADMIN")
        .and().authorizeRequests().antMatchers("UPDATE", "/loanTypes/{id}"). hasAuthority("ADMIN")
        .and().authorizeRequests().antMatchers("/loanTypes/getALLLoanType").hasAnyAuthority("USER")
        .and().authorizeRequests().antMatchers("/loanTypes/conformsLoanType/{amount}").hasAnyAuthority("USER")
        .and().authorizeRequests().antMatchers("PUT", "/loan/**").hasAnyAuthority("BANKER")
        .and().authorizeRequests().antMatchers("GET","/loan/**").hasAnyAuthority("BANKER","USER")
        .and().authorizeRequests().antMatchers("POST","/loan/**").hasAnyAuthority("BANKER","USER")
        .and().authorizeRequests().antMatchers("/bills/**").hasAnyAuthority("BANKER","USER")
        .and().authorizeRequests().antMatchers("POST","/documents/**").hasAnyAuthority("BANKER","USER")
        .and().authorizeRequests().antMatchers("GET","/documents/checkIfWeCanValidate").hasAnyAuthority("BANKER")
        .and().authorizeRequests().antMatchers("GET","/documents/DisplayAllDocuments").hasAnyAuthority("BANKER","USER")
        .and().authorizeRequests().antMatchers("PUT","/documents/updateOneDoc").hasAnyAuthority("USER","BANKER")
        .and().authorizeRequests().antMatchers("/api2").hasAnyAuthority("ADMIN","BANKER","USER")
        .and().authorizeRequests().antMatchers("/api3").hasAnyAuthority("ADMIN","BANKER","USER")
        .and().authorizeRequests().antMatchers("/api4").hasAnyAuthority("ADMIN","BANKER","USER")
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();    
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}