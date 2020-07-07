<%@page import="web.Book"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

<script>
	window.onpageshow = (event) => {
    	if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) location.reload();
	}
</script>
<!DOCTYPE html>
<html>
	
	<head>
	    <title>도서 대여 시스템</title>
	    <meta charset="UTF-8">
	    <title>도서 목록</title>
	
	    <style>
		    footer {
			    position: absolute;
			    left: 0;
			    bottom: 0;
			    width: 100%;
			    padding: 15px 0;
			    text-align: center;
			    color: black;
			    background: #7e868d;
			}
	    	a{text-decoration:none;}
	    	a:hover{text-decoration:underline;}
	        ul, li{display:inline-block;}
	        .form-control-borderless {border: none;}
			.form-control-borderless:hover, .form-control-borderless:active, .form-control-borderless:focus {
			    border: none;
			    outline: none;
			    box-shadow: none;
			}
			select {
			  width: 100px;
			  padding: .8em .5em;
			  font-family: inherit;
			  background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%;  
			  -webkit-appearance: none;
			     -moz-appearance: none;
			          appearance: none;
			  border: 1px solid #999;
			  border-radius: 0px;
			}
			select::-ms-expand {display: none;}
	    </style>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	</head>

	<body>
		<nav style="border-radius:0px;" class="navbar navbar-expand-sm bg-secondary navbar-dark">
			<ul class="navbar-nav">
				<li class="nav-item active">
					<a style="font-size:30px" class="navbar-brand" href="/main/BookList">도서 대여 시스템</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/main/BookList">도서 목록</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/main/rentalCount">대여량 기준 정렬</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/main/rentalBook">대여된 책</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/main/rentalAvailable">대여 가능한 책</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/view/main/rental.jsp">대여</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/main/BookList?p=&f=rental&q=N">반납</a>
				</li>
				<li class="nav-item">
					<a style="font-size:15px" class="nav-link" href="/view/main/addBook.jsp">추가</a>
				</li>
			</ul>
			<form class="form-inline">
				<legend class="hidden">도서 검색</legend>
					<label class="hidden">검색분류</label>
					<select name="f" style="height:34.2px; font-weight:bold; background-color:white; margin-top:10px; margin-left: 100px">
						<option ${(param.f == "name")?"selected":""} value="name">제 목</option>
						<option ${(param.f == "writer")?"selected":""} value="writer">저 자</option>
					</select> 
					<label class="hidden">검색어</label>
					<fieldset>
						<input style="margin-top:10px; width: 300px" class="form-control" placeholder="search for title or name" type="text" name="q" />
					</fieldset>
					<button style="height:34.2px; margin-top:10.3px; border: 1px solid white; background-color: rgba(0,0,0,0); color: white; padding: 5px;" type="submit">검색</button>
			</form>
		</nav>
			<main>	
				<div style="margin-left:10%; margin-right:10%; height: 500px;display:inline;">
					<table class="table table-striped table-hover">
						<h4 style="text-align:center"><b>도서 ${(param.f == "rental")?'반납':'목록'}</b></h3>
						<thead>
							<tr>
								<th style="width:10%; text-align:center">번호</th>
								<th style="width:35%;">제목</th>
								<th style="width:20%; text-align:center">저자</th>
								<th style="width:10%; text-align:center">가격</th>
								<th style="width:10%; text-align:center">대여가능 여부</th>
								<th style="width:10%; text-align:center">대여횟수</th>
								<c:if test="${param.f eq \"rental\" }" >
									<th>반납</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="b" items="${list}" begin="0" end="9">
								<tr onClick="location.href='BookDetail?id=${b.id}'">
									<td style="text-align:center">${b.id}</td>
									<td>${b.name}</td>
									<td style="text-align:center">${b.writer}</td>
									<td style="text-align:center"><fmt:formatNumber value="${b.cost}" />원</td>
									<td style="text-align:center">${b.rental}</td>
									<td style="text-align:center"><fmt:formatNumber value="${b.count}" /></td>
									<c:if test="${param.f eq \"rental\" }" >
										<td ><a style="text-align:center" onClick="alert('도서를 반납하였습니다.')"  href="/main/return?id=${b.id}">반납</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<c:set var="page" value="${(empty param.p)?1:param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${(cnt/10)+(1-((cnt/10)%1))%1}"/>
			
				<div style="margin-left:85%; font-size:15px; font-style:bold" class="">
					<h3 class="hidden">현재 페이지</h3>
					<div><span class="text-orange text-strong">${(empty param.p)?1:param.p}</span> / ${fn:substringBefore(lastNum, '.')} pages</div>
				</div>
	
			
			
	        	<div style="text-align:center" role="toolbar" aria-label="toolbar with buttongroups">
	        		<div style="display:inline-block" role="group" aria-label="First group">
			        	<c:if test="${startNum > 1}">
							<button type="button" class="btn btn-secondary" onClick="location.href='?p=${startNum-1}&f=${(empty param.f)?'':param.f}&q=${param.q}'">이전</button>
						</c:if>
						<c:if test="${startNum < 1}">
							<button type="button" class="btn btn-secondary" onclick="alert('이전 페이지가 없습니다.');">이전</button>
						</c:if>
					</div>
					
			
					<div class="page-item" style="display:inline-block;text-align:center">
						<c:forEach var="i" begin="0" end="4">
							<c:if test="${(startNum+i) <= lastNum}">
								<button type="button" class="btn btn-secondary ${(page==(startNum+i))?'active':''}" onClick="location.href='?p=${startNum+i}&f=${param.f}&q=${param.q}'" >${startNum+i}</button>
							</c:if>
						</c:forEach>		
					</div>
					
					<div class="page-item" style="display:inline-block; text-align:center">
						<c:if test="${startNum+4<lastNum}">
							<button type="button" class="btn btn-secondary" onClick="location.href='?p=${startNum+5}&f=${(empty param.f)?'':param.f}&q=${(empty param.q)?'':param.q}'">다음</button>
						</c:if>
						<c:if test="${startNum+4>=lastNum}">
							<button type="button" class="btn btn-secondary" onClick="alert('다음 페이지가 없습니다.');">다음</button>
						</c:if>
					</div>
				</div>
		</main>
		<footer>
			<div class="container" style="text-align:center">
				<span>가톨릭대학교 컴퓨터정보공학부 객체지향 패러다임</span><br>
				<span>개인 프로젝트: 도서 대여 시스템</span><br>			
				<span>Contact me: wge3142@gmail.com</span><br>
				<span>Copyrightⓒ 2020 don_gun_official All right reserved.</span><br>
			</div>
		</footer>
  </body>
   
</html>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>