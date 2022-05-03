<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="path" value="<%=request.getContextPath()%>"></c:set>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript">
	$(function() {
		var cnt = 1;
		$(document)
				.on(
						"click",
						"div.files button.btn",
						function() {
							cnt++;
							if (cnt > 1) {
								alert("이미지개수는 최대 1개입니다.");
								return false;
							}
							var s = '<input type="file" name="upfile" class="form-control" style="width:250px;">';
							s += '<button type="button" class="btn btn-sm btn-danger" style="width:60px;">추가</button>';
							s += '<br>';
							$("div.files").append(s);
						});
	});
</script>
<title></title>
</head>
<body>
	<form action="cinsert.do" method="post" enctype="multipart/form-data">
		<table align="center" class="table table-bordered" style="width: 500px;">
			<caption>글 쓰기</caption>
			<tr>
				<th style="width: 100px;">작성자</th>
				<td><input type="text" name="com_writer" readonly value="${ loginMember.userid }"></td>
			</tr>
			<tr>
				<th style="width: 100px;">이미지</th>
				<td>
					<div class="files form-group" style="display: inline;">
						<input type="file" name="upfile" class="form-control"
							style="width: 250px;">
						<button type="button" class="btn btn-danger btn-sm"
							style="width: 60px;">추가</button>
						<br>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><textarea
						style="width: 450px; height: 150px;" name="com_content"
						class="form-control">
			</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn btn-success btn-sm">저장</button>
					<button type="button" class="btn btn-danger btn-sm"
						onclick="location.href='blist.do'">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>