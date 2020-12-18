$(function(){
	var parameter = location.search;
	var parameter = parameter.substr(1);
	if(parameter == 'fail'){
		alert('회원정보가 맞지 않습니다');
	}
	$('#loginbtn').click(function(){
		$('#loginform').submit();
	});
	$('#logoutbtn').click(function(){
		$(location).attr('href' , '/fri/hh/logout.fri');
	});
});