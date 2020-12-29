<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>project</title>
<link rel="stylesheet" href="/fri/css/w3.css">
<link rel="stylesheet" href="/fri/css/cls.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="/fri/css/cls.css">
<script type="text/javascript" src="/fri/js/joo/jquery-latest.min.js"></script>
<script type="text/javascript" src="/fri/js/joo/jooNotice.js"></script>

<style>
html, body, h1, h2, h3, h4, h5 {font-family: "Open Sans", sans-serif}
input.dimension2{
	width:140px;
}
div.dimension3{
	width:25px;
}
div.dimension7{
	height:40px;
	margin-bottom:5px;
}
div.dimension8{
	width:90%;
	height:35px;
	margin-bottom:5px;
}
.clickable:hover{
	background-color:beige;
	cursor:pointer;
}
.inline{
	display:inline-block;
}
</style>
</head>
<body class="">

<form method="POST" action="/fri/joo/noticeAnswerProc.fri" id="frm1" name="frm1">
	<input type="hidden" name="nupno" id="nupno" value="">
	<input type="hidden" name="nbody" id="nbody" value="">
</form>
<!-- Page Container -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:20px">    
	<div class="w3-col w3-card w3-margin-bottom w3-round">
		<h1><a href="/fridgeCare/">냉장고를 부탁해!</a></h1>
	</div>
  <!-- The Grid -->
  <div class="w3-row">
    <!-- Left Column -->
    <div class="w3-col m3">
      <!-- Profile --> <!-- 로그인 한 경우 hide 클라스를 지워준다 -->
		<div class="w3-card w3-round w3-white w3-hide">
			<div class="w3-container">
				<h4 class="w3-center">My Profile</h4>
				<p class="w3-center"><img src="/cls/img/avatar/m3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
				<hr>
				<p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> ID</p>
				<p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> Loc</p>
				<p class="w3-btn w3-border w3-hover-blue change05bgc"><i class="fa fa-edit fa-fw w3-margin-right w3-text-theme"></i> edit</p>
			</div>
		</div>
      <br>
      <!-- 로그인 안했을때 -->
		<div class="w3-card w3-round w3-white">
			<form class="w3-container w3-padding" id="frm0">
			 <div class="w3-padding">
			 	<div class="inline dimension3">ID</div>
			 	<input name="inputid" class="dimension2" value="">
			 </div>
			 <div class="w3-padding">
			 	<div class="inline dimension3">PW</div>
			 	<input name="inputid" class="dimension2" value="">
			 </div>
			  <div class="w3-btn w3-border w3-hover-blue change05bgc dimension2" id="loginbtn">
			  	<i class="fa fa-sign-in fa-fw w3-margin-right w3-text-theme"></i> 로그인
			  </div>
			  <div class="w3-btn w3-border w3-hover-blue change05bgc dimension2">
			  	<i class="fa fa-user-plus fa-fw w3-margin-right w3-text-theme"></i> 회원가입
			  </div>
			</form>
		</div>
      <br>
      
      <!-- Accordion -->
      <div class="w3-round">
		<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">My refri</div>
		<div class="w3-gray w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">c1</div>
		<div class="w3-gray w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">c2</div>
		<div class="w3-gray w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">c3</div>
		<div class="w3-gray w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">c4</div>
		<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">Our refri</div>
		<div class="w3-light-blue w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">submit</div>
		<div class="w3-light-blue w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">view</div>
		<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">HQ refri</div>
		<div class="w3-light-blue w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">regist</div>
		<div class="w3-light-blue w3-round-large w3-center dimension8 w3-padding-small inline w3-button w3-hover-cyan change05bgc">view</div>
		<div class="w3-teal w3-round-large w3-center dimension7 w3-padding w3-margin-top w3-button w3-hover-cyan change05bgc" onClick="location.href='notice.fri'">Feedback / Q &amp; A</div>
      </div>
      <br>
      <br>
      
    <!-- End Left Column -->
    </div>
    
<!-- Middle Column --> <!-- 작업할 곳 -->
	<!-- 레시피 테이블 : 이름, 종류, 재료, 영상주소, 조리시간, 상황/
		 이미지테이블 : 업로드 이름, 파일크기, 경로/
		 썸내일 테이블 : 경로, 이름
		 보드 테이블 : 제목  /
		 바디 테이블 : 내용 -->
    <div class="w3-col m9">
      	<div class="w3-container w3-card w3-round w3-margin-bottom w3-margin-left w3-margin-right">
      <!-- 이곳에 꿈과 희망을 펼치세요 -->
      	<div class="w3-col btnfr">
      		<h2 class="w3-button w3-teal nbtn">공지사항</h2>
      		<h2 class="w3-button w3-teal fbtn w3-center">FAQ</h2>
      		<h2 class="w3-button w3-green qbtn w3-right">QNA</h2>
      	</div>
      	<div class="notice" style="display: none;">
			<div class="w3-col w3-padding table-list">
				<div class="w3-col w3-center list">
					<div class="w3-col hd">
						<div class="w3-col m2 no">번 호</div>
						<div class="w3-col m5 title">제 목</div>
						<div class="w3-col m3 date">작 성 일</div>
						<div class="w3-col m2 click">조회수</div>
					</div>
				<c:forEach var="data" items="${LIST}">
					<div class="w3-col cont w3-border-bottom" id="${data.nno}">
						<div class="w3-col m2 no">${data.nno}</div>
						<div class="w3-col m5 title">${data.ntitle}</div>
						<div class="w3-col m3 date">${data.sdate}</div>
						<div class="w3-col m2 click">${data.nclick}</div>
						<div class="w3-col w3-padding w3-margin-bottom body" style="display: none;"></div>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
		<div class="faq" style="display: none;">
			<div class="w3-col w3-padding table-list">
				<div class="w3-col w3-center list">
					<div class="w3-col hd">
						<div class="w3-col m2 no">번 호</div>
						<div class="w3-col m5 title">제 목</div>
						<div class="w3-col m3 date">작 성 일</div>
						<div class="w3-col m2 click">조회수</div>
					</div>
				<c:forEach var="fdata" items="${FLIST}">
					<div class="w3-col cont w3-border-bottom" id="${fdata.nno}">
						<div class="w3-col m2 no">${fdata.nno}</div>
						<div class="w3-col m5 title">${fdata.ntitle}</div>
						<div class="w3-col m3 date">${fdata.sdate}</div>
						<div class="w3-col m2 click">${fdata.nclick}</div>
						<div class="w3-col w3-padding w3-margin-bottom body" style="display: none;"></div>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
      	<div class="qna" style="display: none;">
			<div class="w3-col w3-padding table-list">
				<div class="w3-col w3-center list">
					<div class="w3-col hd">
						<div class="w3-col m2 no">번 호</div>
						<div class="w3-col m5 title">제 목</div>
						<div class="w3-col m3 date">작 성 일</div>
						<div class="w3-col m2 click">조회수</div>
					</div>
				<c:forEach var="qdata" items="${QLIST}">
					<div class="w3-col cont w3-border-bottom" id="${qdata.nno}">
						<div class="w3-col">
							<div class="w3-col m2 no">${qdata.nno}</div>
							<div class="w3-col m5 title">${qdata.ntitle}</div>
							<div class="w3-col m3 date">${qdata.sdate}</div>
							<div class="w3-col m2 click">${qdata.nclick}</div>
						</div>
						<div class="w3-col" style="display: none;">
							<div class="w3-col w3-padding w3-margin-bottom body"></div>
							<div class="m2 w3-right w3-button w3-green w3-padding w3-margin-bottom body mbtn">답변달기</div>
						</div>
						<div class="w3-col w3-padding w3-margin-bottom w3-border-top" style="display: none;"></div>
					</div>
					<div class="w3-col w3-center" style="display: none;">
						<div class="w3-center w700 inblock">
							<h5 class="w3-purple w3-center w3-padding mb10">QnA 답변달기</h5>
							<textarea class="w3-input w3-border h72" id="w${qdata.nno}"></textarea>
							<span class="w3-col m2 w3-left w3-button w3-small w3-lime w3-hover-light-green w3-margin-bottom rbtn">닫기</span>
							<span class="w3-col m2 w3-right w3-button w3-small w3-pink w3-hover-purple w3-margin-bottom wrbtn">완료</span>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>

    <!-- End Middle Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
<!-- End Page Container -->
<!-- </div> -->
</div>

<!-- Footer -->
<footer class="w3-container w3-center w3-theme-d3">
  <h5>Footer</h5>
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>
</body>
</html>