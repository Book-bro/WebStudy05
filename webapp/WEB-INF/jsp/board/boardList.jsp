<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>일련번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="boardList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty boardList }">
				<c:forEach items="${boardList }" var="board">
					<tr>
						<td>${board.rnum }</td>
						<td>${board.boTitle }[${board.attCount }]</td>
						<td>${board.boWriter }</td>
						<td>${board.boMail }</td>
						<td>${board.boDate }</td>
						<td>${board.boHit }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">게시글 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div class="pagingArea">${pagingVO.pagingHTML }</div>
				<form:form id="searchId" modelAttribute="simpleCondition" method="get" onclick="return false;"> <%-- submit을 시킬수 없다 , 데이터를 입력받기 위한 용도이기 때문 --%>
					<form:select path="searchType">
						<option value>전체</option>
						<form:option value="writer" label="작성자"/>
						<form:option value="content" label="내용"/>
					</form:select>
					<form:input path="searchWord"/>
					<input id="searchBtn" type="button" value="검색" /> 
				</form:form>
			</td>
		</tr>
	</tfoot>
</table>
<form:form id="searchForm" modelAttribute="simpleCondition" method="get">
	<form:hidden path="searchType"/>
	<form:hidden path="searchWord"/>
	<input type="hidden" name="page"> <%-- 바인딩을 할 필요가 없음, 매번 바뀌니까, 그래서 input씀 --%>
</form:form>
<script type="text/javascript">
	
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]");
		$.each(inputs, function(index, input){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on("click", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page){
			return false;
		}
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false;
	});
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>