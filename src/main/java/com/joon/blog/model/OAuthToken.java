package com.joon.blog.model;

import lombok.Data;

@Data                       //파싱할때는 setter가 되어야되기때문에 
public class OAuthToken {   //카카오 로그인한 후에 뜨는 정보를 이 오브젝트에 저장, 변수 이름 정확하게 똑같아야함. 안그러면 파싱할때 오류남. 
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private int refresh_token_expires_in;

}
