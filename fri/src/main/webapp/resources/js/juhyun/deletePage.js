$(document).ready(function(){
	$('#sbtn').click(function(){
		var id = $('#id').val();
		if(id == ''){
			alert('조회할 ID를 입력해주세요.');
			return;
		} else{
			$('#frm').submit();
		}
	});
	$('#fbtn').click(function(){
		$('.format').html();
		alert('초기화 되었습니다.');
	});
	$('#dbtn').click(function(){
		$('#dfrm').submit();
	});
});