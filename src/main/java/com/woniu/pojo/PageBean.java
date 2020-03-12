package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
//如果集合的泛型写死，则每个封装类都可能需要一个pagebean，所有在这里集合泛型来定义
public class PageBean<T> {
	private Integer nowPage;
	private Integer pageRow;
	private Integer countRow;
	private Integer countPage;
	private List<T> list;
	private String queryVal;
}
