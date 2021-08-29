package com.joon.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.joon.blog.config.auth.PrincipalDetailService;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게하는 것
@Configuration    // 이 어노테이션을 사용하면 빈 등록됨. ->IOC로 관리됨.
@EnableWebSecurity  //시큐리티 필터가 등록이된다. -> 스프링 시큐리티가 이미 활성화되어있는데(모든 요청url을 다 가로막아) 어떤 설정을 해당 클래스에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을하면 권한 및 인증을 미리 체크하겠다는 뜻. (어떤 요청을 수행하고 인증을 체크하는것이 아님.)
public class SecurityConfig extends WebSecurityConfigurerAdapter{  //쉽게말해 위 어노테이션 3개는 세트라고 생각하면 된다. 
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean  //Bean등록하면 어디서든지 DI해서 쓸 수 있음
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
	@Bean // @Bean등록하면 러턴 값(BCryptPasswordEncoder())을 스프링이 관리한다-> IOC가 된다.-> 필요할 때마다 가져와서 쓰면 된다.
	public BCryptPasswordEncoder encodePWD() {   //시큐리티 내장 함수
		String encPassword = new BCryptPasswordEncoder().encode("1234");   // 1234를 고정길이의 난수 문자열로 암호화해서 encPassword에 저장함.  
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기하는데 해당 password가 뭘로 해쉬가 되어 회원가입 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());  //여기서 비교 알아서 해줌
		}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {  //어떻게 필터링할지 설정.
		
		http
		    .csrf().disable()//csrf 토큰 비활성화  (테스트할 때 걸어두는게 좋음)시큐리티가 기본적으로 csrf토큰이 없으면 막아주는데 user.js에서 csrf토큰없이 요청을 했기때문에->그래서 비활성화시켜야.
			.authorizeRequests() //어떤 요청이 들어오면 
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")   // 근데 그 요청이 /auth/이하,js,css,image/이하,로 들어오는 것이면 
				.permitAll()        //누구나 들어올 수 있다. 
				.anyRequest()       //근데 이게 아닌 다른 요청은
				.authenticated()    //인증이 되어야한다. (로그인을 해야한다.)
		    .and()
		    	.formLogin()
		    	.loginPage("/auth/loginForm")  //인증이 필요하면(/auth/**가 아니면) 모두 이 주소(/auth/loginForm)로 이동한다.   // 로그인 요청이 오는 순간 이 코드가 가로챔->
		    	// 가로챈 아이디와 비밀번호 정보를 principalDetailService클래스에 있는 LoadUserByUsername()에 던진다. -> 던지면 username이 함수 파라미터로 들어가 비교를해서 리턴하고 
		    	// SecurityConfig에 있는 auth.userDetailsService()를 통해서 principalDetailService가 로그인 요청을 하면,  passwordEncoder(encodePWD())에서 
		    	//사용자가 적은 패스워드를 다시 암호화한다. 그리고 데이터베이스와 비교한다. 정상인것을 확인하면 스프링시큐리티 영역에 user정보가 저장이 됨. 
		    	.loginProcessingUrl("/auth/loginProc") //로그인 완료 버튼 누르면, 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해줌. 
	            .defaultSuccessUrl("/"); //정상적으로 로그인이 완료되면 /로 이동. 
	}

}
