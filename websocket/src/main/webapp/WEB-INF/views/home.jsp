<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
<style>
	table{ border-collapse:collapse;}
	td{ padding: 5px 5px}
</style>	
</head>
<script src="js/test.js"></script>
<script>
alert("1111");
function joinLoginCheck(check){
	if(check==1)
		alert("회원가입을 축하합니다.");
	if(check==2)  //로그인 실패시
		alert("아이디나 비번 오류");
}
</script>
<body onLoad="joinLoginCheck(${check})">
<form action="access" name="logFrm" method="post">
	<table border="1">
		<tr>
			<td colspan="2" align="center" bgcolor="aqua">로그인</td>
		</tr>
		<tr>
			<td><input type="text" name="id"/></td>
			<td rowspan="2"><button style="height:50px">로그인</button></td>
		</tr>
		<tr>
			<td><input type="password" name="pw"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center" bgcolor="aqua">com.board.icia</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a href="joinFrm">회원가입</a></td>
		</tr>
	</table>

</form>

</body>
</html>
