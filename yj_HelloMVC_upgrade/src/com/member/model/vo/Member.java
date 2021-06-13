package com.member.model.vo;

import java.util.Date;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {
	
	private String userid;
	private String password;
	private String username;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;

}
