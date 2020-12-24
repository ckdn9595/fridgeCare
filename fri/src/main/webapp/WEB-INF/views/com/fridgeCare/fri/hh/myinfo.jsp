<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>project</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript" src="/fri/js/hh/myinfo.js"></script>
<style>
div.dimension1{
	width:110px;
	height:50px;
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
input.dimension1{
	width:300px;
	height:34px;
	margin:8px;
}
input.dimension3{
	width:90%;
	height:35px;
	margin-bottom:5px;
}
select.dimension1{
	width:200px;
	height:34px;
	margin:8px;
}


.clickable:hover{
	background-color:beige;
	cursor:pointer;
}
.floatleft{
	float:left;
}
body{
	min-width:900px;
}
.margin8{
	margin:8px;
}
.inline{
	display:inline-block;
}
.change05bgc{
	transition: background-color .5s;
}
img{
	
}
</style>
</head>

<body class="">
<c:if test="${empty sessionScope.SID}">
	<c:redirect url="/"></c:redirect>
</c:if>
<!-- Page Container -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:20px; min-width:800px;">    
	<div class="w3-col w3-card w3-margin-bottom w3-round">
		<h1><a href="/fri/hh/main.fri">냉장고를 부탁해!</a></h1>
	</div>
  <!-- The Grid -->
  <div class="w3-row">
    <!-- Left Column -->
    <div class="w3-col m3">
      <!-- Profile -->
      <br>
      
      <!-- Accordion -->
      <div class="w3-round">
		<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">My refri</div>
		<form action="/fri/search.fri" method="get">
			<!-- <input type="hidden" name="catagory" value="한"> -->
			<input class="w3-gray w3-round-large w3-center dimension3 w3-padding-small inline w3-button w3-hover-cyan change05bgc" type="submit" value="레시피 찾기">
		</form>
		<c:if test="${not empty SID}">
			<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">Our refri</div>
			<form action="/fri/recipeAdd.fri" method="get">
				<input class="w3-gray w3-round-large w3-center dimension3 w3-padding-small inline w3-button w3-hover-cyan change05bgc" type="submit" value="내 레시피 작성">
			</form>
		</c:if>
		<div class="w3-blue w3-round-large w3-center dimension7 w3-padding w3-margin-top">HQ refri</div>
		<form action="/fri/juhyun/partner/partner.fri" method="get">
			<input class="w3-gray w3-round-large w3-center dimension3 w3-padding-small inline w3-button w3-hover-cyan change05bgc" type="submit" value="파트너 레시피 보기">
		</form>
		<div class="w3-teal w3-round-large w3-center dimension7 w3-padding w3-margin-top w3-button w3-hover-cyan change05bgc">Feedback / Q &amp; A</div>
      </div>
      <br>
    </div>
    
    
    <!-- Middle Column --> <!-- 작업할 곳 -->
    <div class="w3-col m9" style="min-width:700px">
      
      <form class="w3-container w3-card w3-round w3-margin-bottom w3-margin-left w3-margin-right w3-padding" method="post" action="/fri/hh/infoedit.fri" encType="multipart/form-data">
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">ID</div>
			<input name="inputid" class="floatleft dimension1" id="inputid" disabled value="${MVO.id}">
			<!-- <div class="w3-light-blue w3-round-large w3-center w3-padding-small w3-button w3-hover-cyan change05bgc margin8" id="idcheckbtn">중복확인</div>
			<div class="w3-light-blue w3-round-large w3-center w3-padding-small w3-button w3-hover-cyan change05bgc margin8 w3-hide" id="idresetbtn">아이디 변경</div> -->
		</div>
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">이메일</div>
			<input name="inputmail" class="floatleft dimension1" disabled id="inputmail" value="${MVO.mail}">
			<!-- <div class="w3-light-blue w3-round-large w3-center w3-padding-small w3-button w3-hover-cyan change05bgc margin8" id="mailcheckbtn">중복확인</div> -->
		</div>
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">전화번호</div>
			<input name="inputtel" class="floatleft dimension1" value="${MVO.tel}">
			<div class="w3-padding floatleft w3-right-align"></div>
		</div>
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">연령대</div>
			<c:if test="${MVO.age == 10}">
				<select name="inputage" class="dimension1">
					<option value="10">10대
					<option value="20">20대
					<option value="30">30대
					<option value="40">40대 이상
				</select>
			</c:if>
			<c:if test="${MVO.age == 20}">
				<select name="inputage" class="dimension1">
					<option value="10">10대
					<option value="20" selected>20대
					<option value="30">30대
					<option value="40">40대 이상
				</select>
			</c:if>
			<c:if test="${MVO.age == 30}">
				<select name="inputage" class="dimension1">
					<option value="10">10대
					<option value="20">20대
					<option value="30" selected>30대
					<option value="40">40대 이상
				</select>
			</c:if>
			<c:if test="${MVO.age == 40}">
				<select name="inputage" class="dimension1">
					<option value="10">10대
					<option value="20">20대
					<option value="30">30대
					<option value="40" selected>40대 이상
				</select>
			</c:if>
		</div>
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">거주지역</div>
			<select name="inputloc" class="dimension1">
				<c:if test="${MVO.local == 1}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 2}">
					<option value="1">서울/경기
					<option value="2" selected>강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 3}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3" selected>충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 4}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4" selected>충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 5}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5" selected>경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 6}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6" selected>경상남도
					<option value="7">전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 7}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7" selected>전남/제주
					<option value="8">전라북도
				</c:if>
				<c:if test="${MVO.local == 8}">
					<option value="1">서울/경기
					<option value="2">강원도
					<option value="3">충청남도
					<option value="4">충청북도
					<option value="5">경상북도
					<option value="6">경상남도
					<option value="7">전남/제주
					<option value="8" selected>전라북도
				</c:if>
			</select>
		</div>
		<div class="w3-col m10 w3-margin-left" style="min-width:660px">
			<div class="dimension1 w3-padding floatleft w3-right-align">프로필사진</div>
			<div>
				<img alt="using avt" src="/fri/img/avatar/${AVT}" id="imgpreview" style="height:106px;width:106px">
			</div>
			<input name="inputavt" class="floatleft dimension1" type="file" accept="image/*" onchange="sizecheck(this)" id="inputnewavt">
		</div>
		
		<div class="w3-col m10 w3-center" style="min-width:660px">
			<input class="w3-light-blue w3-round-large w3-center w3-padding w3-button w3-hover-cyan change05bgc margin8" type="submit" id="submitbtn" value="수정">
			<div class="w3-light-blue w3-round-large w3-center w3-padding w3-button w3-hover-cyan change05bgc margin8" id="secessionbtn">탈퇴</div>
		</div>
      </form>
      
    <!-- End Middle Column -->
    </div>
    
    
  <!-- End Grid -->
  </div>
  
<!-- End Page Container -->
</div>
<br>

<!-- Footer -->
<footer class="w3-container w3-theme-d3 w3-padding-16">
  <h5>Footer</h5>
</footer>

<footer class="w3-container w3-theme-d5">
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

</body>
</html>