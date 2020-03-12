package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
//������ϵķ���д������ÿ����װ�඼������Ҫһ��pagebean�����������Ｏ�Ϸ���������
public class PageBean<T> {
	private Integer nowPage;
	private Integer pageRow;
	private Integer countRow;
	private Integer countPage;
	private List<T> list;
	private String queryVal;
}
