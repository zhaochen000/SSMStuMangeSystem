package com.woniu.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {
	private Integer schuID;
	private Integer teacherID;
	private Integer classID;
	private Integer courseID;
	private Integer classRoomID;
	private Date lessonTime;
	private String lessonPlace;
	

}
