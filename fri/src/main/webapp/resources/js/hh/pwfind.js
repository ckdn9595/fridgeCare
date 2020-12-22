$(function(){
	$('#checkbtn').click(function(){
		var inputid = $('#inputid').val();
		var inputmail = $('#inputmail').val();
		$.ajax({
			url:'/fri/hh/sendpwfindmail.fri' ,
			type:'POST' ,
			dataType:'json' ,
			data:{
				id:inputid ,
				mail:inputmail
			} ,
			success:function(data){
				alert(data.result);
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			} // 아직 테스트 안해봄
		})
	});
	$('#idresetbtn').click(function(){
		$('#idresetbtn').addClass('w3-hide');
		$('#idcheckbtn').removeClass('w3-hide');
		$('#submitbtn').attr('disabled' , true);
		$('#inputid').attr('readonly' , false);
	});
	$('#mailcheckbtn').click(function(){
		var smail = $('#inputmail').val();
		if(smail.length < 1){
			return;
		}
		$.ajax({
			url:'/fri/hh/mailcheck.fri' ,
			type:'POST' ,
			dataType:'json' ,
			data:{
				mail:smail
			} ,
			success:function(data){
				if(data.result == 'OK'){
					var c = confirm('사용가능한 메일입니다\n사용하시겠습니까?');
					if(c){
					//	$('#mailcheckbtn').addClass('w3-hide');
						$('#submitbtn').attr('disabled' , false);
					}else{
						$('#submitbtn').attr('disabled' , true);
					}
				}else{
					alert('이미 사용중인 메일입니다');
					$('#submitbtn').attr('disabled' , true);
				}
			} ,
			error:function(){
				alert('잠시후 다시 시도해주십시오');
			}
		})
	});
	$('#inputmail').keyup(function(){
		$('#submitbtn').attr('disabled' , true);
	});
	$('#inputpwre').keyup(function(){
		var pwrule = /^[a-zA-Z1-9]{6,12}$/;
		if(pwrule.test($('#inputpw').val())){
			if($('#inputpw').val() == $('#inputpwre').val()){
				$('#submitbtn').removeClass('w3-disabled');
				$('#pwrecheck').html('일치');
			}else{
				$('#submitbtn').addClass('w3-disabled');
				$('#pwrecheck').html('불일치');
			}
		}else{
			$('#submitbtn').addClass('w3-disabled');
			$('#pwrecheck').html('형식에 맞지 않음');
		}
	});
});
function sizecheck(input){
	 if (input.files && input.files[0].size > (10 * 1024 * 1024)) {
        alert("10메가 이하만 가능합니다");
        input.value = null;
    }
}