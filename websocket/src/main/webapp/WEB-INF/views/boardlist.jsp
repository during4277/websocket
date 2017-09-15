<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.1.1.min.js">
</script>
<style type="text/css">
  /*table, td, th{border:1px solid gray;}
  th{ background-color:gray; color:green;}
  */
  html, body{height:100%;margin:0}
  #articleView_layer {
			display:none;position:fixed;
			position:absolute;top:0;left:0;
			width:100%;height:100%}
  #articleView_layer.open {display:block;color:red}
  #articleView_layer #bg_layer {
			   position:absolute;top:0;left:0;
			   width:100%;height:100%;background:#000;
			   opacity:.5;filter:alpha(opacity=50);z-index:100}
  #contents_layer { position:absolute;top:40%;left:40%;
	   	width:400px;height:400px;margin:-150px 0 0 -194px;
 	   	padding:28px 28px 0 28px;border:2px solid #555;
   	   	background:#fff;font-size:12px;z-index:200;
	   	color:#767676;line-height:normal;white-space:normal;
	   	overflow:scroll}
</style>
</head>
<body>
<div align="right">
	<a href="logout">로그아웃</a>
</div>
<table id="one_table">
	<tr height="30">
		<td width="80" bgcolor="pink" align="center"> ID</td>
		<td>${member.id}</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center"> NAME</td>
		<td>${member.name}</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center"> GNAME</td>
		<td>${member.gname}</td>
	</tr>
	<tr height="30">
		<td width="80" bgcolor="pink" align="center"> POINT</td>
		<td>${member.point}</td>
	</tr>
</table>
<br/>
<table id="two_table">
	<tr bgcolor="aqua" height="30">
		<th width="100">번호</th>
		<th width="100">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
	
	<c:forEach var="board" items="${blist}">
		<tr height="25">
			<td align="center">${board.bnum}</td>
			<td align="center"><a href="#contents_layer" onclick="articleView(${board.bnum})">
			                         ${board.btitle} </a></td>
			<td align="center">${board.bid}</td>
			<td align="center">${board.bdate}</td>
			<td align="center">${board.bviews}</td>
	</c:forEach>
</table>
<form action="writeFrm" >
	<button>글쓰기</button>
</form> 
<div align="center">
${paging}
</div>
<script>
$(function() {  //$(document).ready(funtion(){})
	var layerWindow=$('#articleView_layer');
	layerWindow.find('#bg_layer').mousedown(
			function(event){
				//console.log(event);
				layerWindow.removeClass('open');
				return;
			}); //find end
	$(document).keydown(function(event){
			console.log(event);
			if(event.keyCode!=27) return;
			if(layerWindow.hasClass('open')){
				layerWindow.removeClass('open');
			}
			return;
		} //function End 
	);//keydown End
});
function articleView(num){
	//alert(num);
	$('#articleView_layer').addClass('open');
	$.ajax({type:'post',
			url:'contents',
			data:{bnum:num},
			//dataType:'html','json','xml'
			success:function(data){
				//alert(data);
				//console.log(data);
				$('#contents_layer').html(data);
			},
			error:function(error){
				alert("error");
				console.log(error);		
			}
	}); //ajax end
}//articleView end

</script>
</body>
<div id="articleView_layer">
	<div id="bg_layer"></div>
	<div id="contents_layer"></div>
</div>
</html>