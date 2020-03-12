package com.woniu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Integer  uid;
	private  String uname;
	private String upwd;
	private String uemail;
	
	

}
