package kr.co.trgtech.trg01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.trgtech.trg01.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.csrf() 										//cors해제
    			.disable()
    		.authorizeRequests() 							//set authenticated each url
    			.antMatchers("/*").permitAll()//성능테스트로 인해 모든 인증 풀었음(성능테스트 후 삭제)
        		.antMatchers("/loginPage").permitAll()
        		.antMatchers("/signupPage").permitAll()
        		.antMatchers("/signup").permitAll()
        		.antMatchers("/test").permitAll()
        		//.antMatchers("/**").authenticated()
        		.and()
        	.formLogin() 									//set login
        		.loginPage("/loginPage")
        		.loginProcessingUrl("/loginPro")
        		.usernameParameter("loginId")
        		.passwordParameter("passwd")
        		.defaultSuccessUrl("/channelPage/") 		//로그인 성공 후 이동페이지
        		.permitAll()
        		.and()
        	.logout() 										//set logout
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/loginPage")
        		;
    }

    //login처리
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    
}
