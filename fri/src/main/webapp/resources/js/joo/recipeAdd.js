$(document).ready(function(){
	$('#sabtn1').click(function(){
		$('#second').css('display', 'block');
	});
	$('#sabtn2').click(function(){
		var tmp = $('#thumb').val();
		if(!tmp){
			alert('썸네일이 등록되지 않았습니다.');
			return;
		}
		$('#frm').submit();
	});
	
	$('#inbtn').click(function(){
		var txt = $('.inbox').html();
		$('#selfr').append(
			'<select class="inbox w3-col m7 txt14 mabottom" name="inboxList">'+txt+'</select>'
		);
	});
	
	var txt2 = $('#reci').html();
	$('#rebtn').click(function(){
		$('#reci').append(
			txt2
		);
		$('.image').off();
		$('.image').change(function(e){
			var ipath = URL.createObjectURL(e.target.files[0]);
			$(this).next().next().attr('src', ipath);
			var tmp2 = $(this).val();
			tmp2 = tmp2.substring(tmp2.lastIndexOf('\\') + 1);
			$(this).next().val(tmp2);
		});
	});
	
	$('#thumb').change(function(e){
		var ipath = URL.createObjectURL(e.target.files[0]);
		$('#tImg').attr('src', ipath);
	});
	
	$('.image').change(function(e){
		var ipath = URL.createObjectURL(e.target.files[0]);
		$(this).next().next().attr('src', ipath);
		var tmp2 = $(this).val();
		tmp2 = tmp2.substring(tmp2.lastIndexOf('\\') + 1);
		$(this).next().val(tmp2);
	});
	
});