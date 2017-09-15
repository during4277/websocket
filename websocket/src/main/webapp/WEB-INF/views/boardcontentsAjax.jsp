<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<body>
<h1> Board & reply Contents</h1>
<table>
	<tr height="30">
		<td width="100" bgcolor="pink" align="center">NUM</td>
		<td colspan="5">${board.bnum}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink" align="center">WRITER</td>
		<td width="150">${board.bid}</td>
		<td bgcolor="pink" align="center">DATE</td>
		<td width="150">${board.bdate}</td>
		<td bgcolor="pink" align="center">VIEWS</td>
		<td width="150">${board.bviews}</td>
	</tr>
	<tr height="30">
		<td bgcolor="pink"	align="center">TITLE</td>
		<td colspan="5">${board.btitle}</td>
	</tr>
	<tr height="200">
		<td bgcolor="pink"	align="center">CONTENTS</td>
		<td colspan="5">${board.bcontents}</td>
	</tr>
</table>
<hr/>
<form name="rFrm" id="rFrm">
<table>
	<tr>
		<td>
			<textarea rows="3" cols="50" id="comment"></textarea>
		</td>
		<td>
			<input type="button" onclick="replyInsert(${board.bnum})"	
				value="답글전송" style="width:70px;height:50px" />				
</table>
</form>

<table>
	<tr bgcolor="aqua" align="center" height="30">
		<td width="100">작성자</td>
		<td width="200">답글내용</td>
		<td width="150">작성일</td>
	</tr>	
</table>
<table id="rTable">
	<c:forEach var="reply" items="${rlist}">
		<tr height="25" align="center">
			<td width="100">${reply.rid}</td>		
			<td width="200">${reply.rcontents}</td>
			<td width="150">${reply.rdate}</td>
		</tr>
	</c:forEach>
</table>
<script src="resources/js/jquery-3.1.1.min.js">
</script>
<script>
function replyInsert(bnum){
	$.ajax({
		type:'get',
		url:'rest/replyInsert',
		data:{bnum:bnum, comment:$('#comment').val()},
		   //$('#rFrm').serialize(),폼 전체 데이터 전송 
		dataType:'json',   
		success:function(data){
			/*jackson 구조 */
			/*console.log(data); //구조보고 js코딩할 수 있다.
			console.log(data.rlist);
			console.log(data.rlist.length);
			console.log(data.rlist[0].rid);
			var rlist=data.rlist;
			console.log(rlist[0].rid);
			*/
			var rlist='';
			for(var i=0;i<data.length;i++){
				rlist+='<tr height="30" align="center">'
				+'<td width="150">'+data[i].rid+'</td>'
				+'<td width="320">'+data[i].rcontents+'</td>'
				+'<td width="150">'+data[i].rdate+'</td></tr>'
			}
			$('#rTable').html(rlist);
		}   
	});
}

</script>
</body>
</html>