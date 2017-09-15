<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
 .subject{text-align:center;height:40px;}
</style>
</head>
<body>
<form name="joinFrm" action="memberInsert" method="post"
		onsubmit="return check()">
<table>
	<tr>
		<td colspan="2" class="subject">회원가입</td>
	</tr>
	<tr>
		<td width="80">ID</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td>PWD</td>
		<td><input type="password" name="pw"></td>
	</tr>
	<tr>
		<td>NAME</td>
		<td><input type="text" name="name"></td>	
	</tr>
	<tr>
		<td>BIRTH</td>
		<td><input type="text" name="mbirth"></td>	
	</tr>	
	<tr>
		<td>ADDR</td>
		<td><input type="text" name="maddr"></td>	
	</tr>
	<tr>
		<td>PHONE</td>
		<td><input type="text" name="mphone"></td>	
	</tr>
	<tr>
		<td colspan="2" class="subject" >
		<input type="submit" value="회원가입"></td>
	</tr>	
</table>		
</form>
<script>
function check(){
	var frm=document.joinFrm;
	var length=frm.length-1;
	
	for(var i=0;i<length; i++){
		if(frm[i].value==""){
			alert(frm[i].name+"을 입력하세요");
			frm[i].focus();
			return false;
		}
	}
	return true;
}
</script>		
</body>
</html>