package com.joon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joon.blog.config.auth.PrincipalDetail;
import com.joon.blog.model.KakaoProfile;
import com.joon.blog.model.OAuthToken;
import com.joon.blog.model.User;
import com.joon.blog.service.UserService;


// 인증이 안된 사용자들이 출입하 수 있는 경로를 /auth/..이하경로로 허용.
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;   //카카오 로그인한 사람의 모든 비밀번호는 cosKey값으로 통일할 것임. 절대 노출되면 안됨.
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/joinForm")  //회원가입하러 들어가는데 인증이 있을 필요는 없음. ->인증이 필요없는 것에는 다 auth를 붙임
	public String joinForm() {
		return "user/joinForm";
	}
	
	
	@GetMapping("/auth/loginForm")// ->인증이 필요없는 것에는 다 auth를 붙임
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {  //Data를 리턴해주는 컨트롤러 함수
		
		//여기서 POST방식으로 카카오쪽으로 key = value 타입의 데이터를 요청해야한다. 
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");  //내가 전송할 http body 데이터가 key = value 타입임을 알려주는 코드
		
		//HttpBody오브젝트 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();  //바디데이터를 담을 오브젝트를 만듦
		params.add("grant_type", "authorization_code");
		params.add("client_id", "c0a13425dd6eb7604d1ab2f9f1754aa6");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers); //kakaoTokenRequest는 바디데이터와 header값을 가진 엔티티가 됨.
		
		//Http 요청하기 - Post방식으로 -그리고 response변수의 응답 받음. 
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",  //토큰 
				HttpMethod.POST,  //요청 매소드 종류
				kakaoTokenRequest,  //바디데이터와 header값을 가진 엔티티가
				String.class      //응답을 받을 타입 String으로 응답받음
		);	
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("카카오엑세스 토큰:"+oauthToken.getAccess_token());
		
		
		//사용자 정보 요청하기
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");  //내가 전송할 http body 데이터가 key = value 타입임을 알려주는 코드
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2); //kakaoTokenRequest는 바디데이터와 header값을 가진 엔티티가 됨.
		
		//Http 요청하기 - Post방식으로 -그리고 response변수의 응답 받음. 
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",  //토큰 
				HttpMethod.POST,  //요청 매소드 종류
				kakaoProfileRequest2,  //바디데이터와 header값을 가진 엔티티가
				String.class      //응답을 받을 타입 String으로 응답받음
		);	   
		
		System.out.println(response2.getBody());  //인증완료되면 코드 값 받아옴.
		
		
		
		//KakaoProfile 객체에 정보넣기
		ObjectMapper objectMapper2 = new ObjectMapper();
		
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//User 오브젝트: username, password, email
		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
		System.out.println("카카오 이메일:"+kakaoProfile.getKakao_account().getEmail());
		
		//위에서 카카오로부터 받아온 카카오아이디와 카카오이메일을 통해서 우리 블로그에 아래와같이 회원가입시킬 것이다.
		System.out.println("블로그서버 유저네임:"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일:"+kakaoProfile.getKakao_account().getEmail());
		System.out.println("블로그서버 패스워드:"+cosKey);  // .yml에서 설정한 cosKey를 패스워드로. 
		
		//유저오브젝트 만들어서 회원가입시키기(model 패키지의 User)
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
				
		//가입자인지 비가입자인지 체크해서 처리해야함.
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		//비가입자면 회원가입
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아닙니다......!!!");
			userService.회원가입(kakaoUser);
		}
		
		System.out.println("자동로그인을 진행합니다.");
		// 자동 로그인처리
		//1. Authentication객체 생성    //user.getUsername(),user.getPassword()는 user.js파일에서 받아와야
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		//2. Authentication객체 세션에 등록.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}	
	
	@GetMapping("/user/updateForm")// ->인증이 필요없는 것에는 다 auth를 붙임
	public String updateForm() {
		return "user/updateForm";
	}
} 







