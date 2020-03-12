package com.woniu.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewInfo {
	
	private int infoID;
	private String InfoName;
	private String infoAdd;
	private String publishDate;
	private String infoContent;


}
