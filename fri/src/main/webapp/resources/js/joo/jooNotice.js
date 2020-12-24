$(document).ready(function(){
	$('.cont').click(function(){
		$('.body').css('display', 'none');
		var sno = $(this).attr('id');
		var el = $(this);
		var tmp = $(el).children().last().html();
		if(!tmp){
			$.ajax({
				url: '/fri/joo/noticeBody.fri',
				type: 'POST',
				dataType: 'json',
				data: {
					nno: sno
				},
				success: function(obj){
					$(el).children().last().text(obj.nbody);
					$(el).children().eq(3).text(obj.nclick);
					$(el).children().last().stop().slideDown(200);
				},
				error: function(){
					alert('### error ###')
				}
			});
		} else {
			$(el).children().last().stop().slideDown(200);
		}
	});
	
	$('.nbtn').click(function(){
		$('.faq').css('display','none');
		$('.notice').css('display','block');
	});
	$('.fbtn').click(function(){
		$('.notice').css('display','none');
		$('.faq').css('display','block');
	});
});