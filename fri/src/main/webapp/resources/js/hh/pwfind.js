$(function(){
	$('#checkbtn').click(function(){
		var inputid = $('#inputid').val();
		var inputmail = $('#inputmail').val();
		if(inputid == ''){
			return;
		}
		$.ajax({
			url:'/fri/hh/sendpwfindmail.fri' ,
			type:'POST' ,
			dataType:'json' ,
			data:{
				id:inputid ,
				mail:inputmail
			} ,
			success:function(data){
				if(data.result == 'OK'){
					alert('메일을 보냈으니 가서 인증번호를 알아와라');
					$('#ANdiv').removeClass('w3-hide');
				}else{
					alert(data.result);
				}
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			}
		})
	});
	$('#checkbtn2').click(function(){
		var notdata = $('#nottest .ntc').not('.w3-hide').children('span').get();
		var totaldata = '';
		for(var i = 0; i < notdata.length; i++){
			totaldata += notdata[i].innerHTML + '|';
		}
		alert(totaldata);
	});
});