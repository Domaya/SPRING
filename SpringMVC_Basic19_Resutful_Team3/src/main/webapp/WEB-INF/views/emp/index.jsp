<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
   
<title>Insert title here</title>
<style>
body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}
  
 @media all and (max-width: 768px) {
    
  table, thead, tbody, th, td, tr {
    display: block;
  }
  
  th {
    text-align: right;
  }
  
  table {
    position: relative; 
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0,0,0,.2);
  }
  
  thead {
    float: left;
    white-space: nowrap;
  }
  
  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }
  
  tr {
    display: inline-block;
    vertical-align: top;
  }
  
  th {
    border-bottom: 1px solid #a39485;
  }
  
  td {
    border-bottom: 1px solid #e5e5e5;
  }
  
  
  }
  </style>

</head>
<body>
	<h3>EMP</h3>

	 <span id="menuView"></span>
	<hr>

</body>
<script type="text/javascript">
//$(document).ready(function(){
    $.ajax(
            {  
              type : "get",
              url  : "emp",
              success : function(data){
                 createTable(data, "@ResponseBody");
              } 
            } 
          ) 
      function createTable(data, way){
        $('#menuView').empty();
        var opr="<table id='fresh-table' class='table'><tr>"+way+"</tr><thead><tr>"+
            "<th>EMPNO</th>"+
              "<th>ENAME</th>"+
              "<th>SAL</th>"+
              "<th>EDIT</th><th>DELETE</th></tr></thead><tbody>";
        $.each(data,function(index,emp){
        	console.log(data);
           opr += "<tr><td>"+emp.empno+
           "</td><td>"+emp.ename+
           "</td><td>"+emp.sal+
           "</td><td><input type='button' onclick='empupdate(this)' value='수정' class ='update'  value2="+emp.empno+
           "></td><td><input type='button' value='삭제' class ='delete' value2="+emp.empno+"></td></tr>";
        });
        opr+="<tr><td colspan='10'><input type='button' onclick='createinput(this)' value='추가'></td></tr></tbody></table>";
        $('#menuView').append(opr);
     }
    //삭제
    $(document).on("click",".delete",function(){
        console.log($(this).attr("value2"));
        var empno =  $(this).attr("value2");
        console.log("ㅋㅋ " + empno)
        $.ajax({
           type : "delete",
           url  : "emp/" + empno,
           data : {empno : $(this).attr("value2")},
           success : function(list){ 
              createTable(list, "삭제완료");
           } 
        })
     });
//    })

    //등록폼
    function createinput(me){
    	  var tr = $(me).closest('tr');
          tr.empty();
          var td = "<td><input type='text'></td>";
          td +="<td><input type='text'></td>";
          td +="<td><input type='text'></td>";
          td +="<td><input type='button'onclick='empinsert(this)' value='완료'/></td>";
          td +="<td><input type='button'onclick='cancel(this)' value='취소'/></td>";
          $(tr).append(td); 
    }
    //등록처리
    function empinsert(me){
        var tr = $(me).closest('tr');
        var data = {empno:tr.find("td:eq(0)").children().val(),
                 ename:tr.find("td:eq(1)").children().val(),
                 sal:tr.find("td:eq(2)").children().val(),
                 };
        console.log(data);
        $.ajax({
           type : "post",
           contentType: 'application/json',
           url:"emp",
           data:JSON.stringify(data),
           success : function(myemp){  
               console.log(myemp);
               createTable(myemp, "수정완료");
           } 
        })
     }
    
    
    //수정폼
    function empupdate(me){
        var tr = $(me).closest('tr')
        var datas = {empno:tr.children().html()};
        var empno = tr.children().html();
        tr.empty();
        console.log("datas" + datas)
        $.ajax({
           type : "get",
           url:"emp/"+empno,
           //data:JSON.stringify(datas),
           data:empno,
           contentType: 'application/json',
           success : function(data) {
        	   console.log(data);
               var td = "<td><input type='text' value='"+data.empno +"' readonly></td>";
                 td +="<td><input type='text' value='"+data.ename +"'></td>";
                 td +="<td><input type='text' value='"+data.sal +"'></td>";
                 td +="<td colspan='2'><input type='button'onclick='empupdateconfirm(this)' value='완료' value2="+data.empno+" /></td>";
                 $(tr).append(td); 
           }
        })
     }
    function empupdateconfirm(me){         
       empupdateok(me);
    }
    //수정 처리
    function empupdateok(me){
       var tr = $(me).closest('tr');
       var data = {empno:tr.find("td:eq(0)").children().val(),
                ename:tr.find("td:eq(1)").children().val(),
                sal:tr.find("td:eq(2)").children().val(),
                };
       $.ajax({
          type : "put",
          url:"emp",
          contentType: 'application/json',
          data:JSON.stringify(data),
          success : function(data){  
             console.log(data);
             createTable(data, "수정완료");
          } 
       })
    }
    
    //취소버튼
    function cancel(me){
        var tr = $(me).closest('tr');
        tr.empty();
        tr.append("<td colspan='10'><input type='button' onclick='createinput(this)' value='추가'></td>");
     }
    
</script>
</html>