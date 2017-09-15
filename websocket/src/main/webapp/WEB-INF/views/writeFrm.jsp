<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<style>
			table{
				width:100%;
			}
			table, td, th{
				border : 1px solid;
				border-collapse : collapse;
				padding: 5px;
			}
			
			input[type='text']{
				width:100%;
			}
			textarea{
				width:100%;
				resize:none;
			}
			
		</style>
<script>
</script>
</head>
<body><form action="boardWrite" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>제목</td>
		<td><input type="text" name="btitle"/></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="bcontents" rows="20"></textarea></td>
	</tr>
	<tr>
		<td>파일첨부</td>
		<td><input id="bfile" type="file" name="bfile" onChange="fileChk(this)" />
			<input id="fileCheck" type="hidden" name="fileCheck"/>
		</td>
		    
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="글작성" />
			<input type="reset" name="취소"/>
			<input type="button" onclick="location.href='./boardlist'"
			value="리스트 보기" />
		</td>	
	</tr>	
</table>
</form>
</body>
<script>

function fileChk(elem){
	console.log(elem.value);
	
	if(elem.value=="")
		console.log("empty");	
	if(elem.value=="")
		$("#fileCheck").val(0);
	else
		$("#fileCheck").val(1);
}

</script>
</html>