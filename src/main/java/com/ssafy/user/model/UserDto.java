	package com.ssafy.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private String birth;
	private int userSidoId;
	private int userGuId;
	private String joinDate;
	private String salt;
	private int gradeId;
	private String token;
}
