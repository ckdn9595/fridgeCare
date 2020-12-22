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
	$('#myinfobtn').click(function(){
		$(location).attr('href' , '/fri/hh/myinfo.fri');
	});
	$('#luvoform').click(function(){
		$(this).submit();
	});
	$('#wvoform').click(function(){
		$(this).submit();
	});
	$('#mvoform').click(function(){
		$(this).submit();
	});
	$('#ajaxtest').click(function(){
		var Situat = ['aa' , 'ab' , 'bb'];
		var stringdata = 'abc';
		$.ajax({
			url:'/fri/hh/ajaxtest.fri' ,
			type:'POST' ,
			dataType:'json' ,
			traditional:true ,
			data:{
				ajaxdata:Situat
			} ,
			success:function(data){
				alert(Situat);
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			}
		})
	});
});