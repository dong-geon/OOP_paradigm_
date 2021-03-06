<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

<!DOCTYPE html>
<html>
	<head>
	    <title>도서 대여 시스템</title>
	    <meta charset="UTF-8">
	    <title>도서 대여</title>
	    
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
	        ul, li{
	        	display:inline-block;
	        }
	        .form-control-borderless {
			    border: none;
			}
			
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
			
			select::-ms-expand { /* for IE 11 */
			    display: none;
			}
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
			<form action="http://localhost:8080/main/BookList?f=&q=" class="form-inline">
				<legend class="hidden">도서 검색</legend>
					<label class="hidden">검색분류</label>
					<select name="f" style="height:34.2px; font-weight:bold; background-color:white; margin-top:10px; margin-left: 100px">
						<option ${(param.f == "name")?"selected":""} value="name">제 목</option>
						<option ${(param.f == "writer")?"selected":""} value="writer">저 자</option>
					</select> 
					<label class="hidden">검색어</label>
					<fieldset>
						<input style="margin-top:10px; width: 300px" class="form-control" placeholder="search for title or name" type="text" name="q" value="${param.q}"/>
					</fieldset>
					<button style="height:34.2px; margin-top:10.3px; border: 1px solid white; background-color: rgba(0,0,0,0); color: white; padding: 5px;" type="submit">검색</button>
			</form>
		</nav>
		<div style="margin-top:20px; height:550px;">
			<H4 style="text-align:center"><b>도서 대여</b></H4>
	     	<div style="width:100%">
     			<form style="margin-top:10px; margin-left:26.7%" ACTION="/main/rental" METHOD="post" class="form-horizontal">
					<div class="form-group" style="width:90%">
					    <label class="col-sm-2 control-label">아이디</label>
					    <div class="col-sm-10" style="width:75%">
					      <input style="width:53%; align:center" type="text" class="form-control" name="id" value="" placeholder="대여할 도서의 ID를 입력하세요">
					    </div>
					</div>
					<div class="form-group" style="width:90%">
					    <div class="col-sm-offset-2 col-sm-10" style="width:75%">
					      <input style="width:53%" type="submit" value="대여" class="btn btn-secondary" />
					    </div>
					</div>
				</form>
			</div>
		</div>
				
			
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
