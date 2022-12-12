<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 화면</title>
</head>
<body>

<!-- 주문 발생
OrderCommand command = new OrderCommand();

List<OrderItem> itemlist = new ArrayList<>();
itemlist.add(new OrderItem(1, 10, "파손 주의"));
itemlist.add(new OrderItem(10, 1, "리모컨 별도 구매"));

command.setOrderItem(itemlist); // 주문한 물건들
 -->
<form method="post">
상품 1<br>
상품 ID: <input type="text" name="orderItem[0].itemid">
상품 수량: <input type="text" name="orderItem[0].number">
상품 주의사항: <input type="text" name="orderItem[0].remark"><br>
<hr>
상품 2<br>
상품 ID: <input type="text" name="orderItem[1].itemid">
상품 수량: <input type="text" name="orderItem[1].number">
상품 주의사항: <input type="text" name="orderItem[1].remark"><br>
<hr>
상품 3<br>
상품 ID: <input type="text" name="orderItem[2].itemid">
상품 수량: <input type="text" name="orderItem[2].number">
상품 주의사항: <input type="text" name="orderItem[2].remark"><br>
<hr>
<input type="submit" value="전송">
</form>
</body>
</html>