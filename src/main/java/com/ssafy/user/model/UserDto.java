package com.ssafy.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
//
//	private String userId;
//	private String userName;
//	private String userPassword;
//	private String gender;
//	private String birthdate;
//	private String phone;
//	private String emailId;
//	private String emailDomain;
//	private String salt;
//	private String grade;
	
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private String birth;
	private String userSidoId;
	private String userGuId;
	private String joinDate;
}
