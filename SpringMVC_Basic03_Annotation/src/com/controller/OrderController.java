package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.OrderCommand;
import com.model.OrderItem;

/*
하나의 요청 주소로 2개의 업무처리
/order/order.do
  
GET > 화면주세요
POST > 처리해주세요
  
*/
@Controller
@RequestMapping("/order/order.do")
public class OrderController {

	@GetMapping
	public String form() {
		return "order/OrderForm";
	}
	
	@PostMapping
	public String submit(OrderCommand ordercommand) {
		/*
		자동화된 코드를 보여드릴게요
		1. OrderCommand ordercommand = new OrderCommand();
		1.1 자동 매핑 >>member field >>  private List<OrderItem> orderItem 자동 주입
		
		2. List<OrderItem> item = new ArrayList<>();
		   >>>> orderItem[0].itemid > 1
		   >>>> orderItem[0].number > 10
		   >>>> orderItem[0].remark > 주의하세요
		   item.add(new OrderItem(1,10,"주의하세요")
		   
		   ...
		   item.add(new OrderItem(2,10,"주의하세요")
		   ...
		   item.add(new OrderItem(3,10,"주의하세요")
		   
		   ordercommand.setOrderItem(item);
		   
		   원리 : key 값 (view)  >> OrderCommand >> orderCommand
		//   mv.addObject("orderCommand", ordercommand);
		//   mv.setViewName("order/OrderCommitted");
		
		*/
		return "order/OrderCommitted";
	}
}



