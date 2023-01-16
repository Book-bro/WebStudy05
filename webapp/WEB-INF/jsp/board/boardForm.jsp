<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor/ckeditor.js"></script>
<form:form modelAttribute="board" enctype="multipart/form-data" method="post">
<table class="table table-bordered">
	<tr>
		<th>글번호</th>
		<td>
			<form:input cssClass="form-control" type="number" path="boNo" readonly="true"/>
			<form:errors path="boNo" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>게시글 제목</th>
		<td>
			<form:input cssClass="form-control" path="boTitle"/>
			<form:errors path="boTitle" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<form:input cssClass="form-control" type="text" path="boWriter" />
			<form:errors path="boWriter" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>
			<input type="password" name="boPass" class="form-control"/>
			<form:errors path="boPass" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>
			<input name="boIp" type="text" readonly value="${pageContext.request.remoteAddr }"/>
			<form:input cssClass="form-control" type="text" path="boIp" />
			<form:errors path="boIp" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>
			<form:input cssClass="form-control" type="text" path="boMail" />
			<form:errors path="boMail" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<input type="file" name="boFiles" />
			<input type="file" name="boFiles" />
			<input type="file" name="boFiles" />
		</td>
	</tr>
	<tr>
		<th>게시글 내용</th>
		<td>
			<form:textarea path="boContent"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" />
		</td>
	</tr>
</table>
</form:form>
<script>
	CKEDITOR.replace('boContent');
</script>
