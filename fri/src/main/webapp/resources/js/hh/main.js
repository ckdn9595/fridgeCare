$(function(){
	var parameter = location.search;
	var parameter = parameter.substr(1);
	if(parameter == 'fail'){
		alert('회원정보가 맞지 않습니다');
	}
	switch(parameter){
		case 'pwchange':alert('비밀번호가 변경되었습니다');
		break;
		case 'secession':alert('탈퇴 성공');
		break;
	}
	$('#loginbtn').click(function(){
		var inputid = $('input[name="inputid"]').val();
		if(inputid == ''){
			return;
		}
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
		$.ajax({
			url:'/fri/hh/ajaxtest.fri' ,
			type:'POST' ,
			dataType:'json' ,
			traditional:true ,
			data:{
				ajaxdata:Situat
			} ,
			success:function(){
				alert(Situat);
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			}
		})
	});
	$('#test2').click(function(){
		$.ajax({
			url:'/fri/hh/mailtest.fri' ,
			type:'POST' ,
			dataType:'json' ,
			data:{
				ajaxdata:'call'
			} ,
			success:function(data){
				alert(data.result);
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			}
		})
	});
});