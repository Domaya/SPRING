package com.model;

import lombok.Data;

//상품 테이블 있다고 가정
//1:1 매핑

@Data
public class OrderItem {
	private int itemid;
	private int number;
	private String remark;
	
	//setter , getter , toString()
}