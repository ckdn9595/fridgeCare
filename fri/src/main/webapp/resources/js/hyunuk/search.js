$(document).ready(function(){
	var ingred ='';
	var Situat = [];     // 상황 배열
	var Category = [];   // 카테고리 배열
	var arr = [];
	
	$('.ingbtn1').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .meat').removeClass('w3-hide');
	});

	$('.ingbtn2').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .bean').removeClass('w3-hide');
	});
	$('.ingbtn3').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .vegetable').removeClass('w3-hide');
	});
	$('.ingbtn4').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .fruit').removeClass('w3-hide');
	});
	$('.ingbtn5').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .seafood').removeClass('w3-hide');
	});
	$('.ingbtn6').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing  .seasoning').removeClass('w3-hide');
	});
	$('.ingbtn7').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .dairy').removeClass('w3-hide');
	});
	$('.ingbtn8').click(function(){
		$('.iList').addClass('w3-hide');
		$('#ing .noodles').removeClass('w3-hide');
	});
	
	$('.iList').click(function(){
		var iname = $(this).children('').html();
		$('.' + iname).removeClass('w3-hide');
	});
	
	$('.sList').click(function(){
		$(this).addClass('w3-hide');
	});
	
	
	$('#checkbtn2').click(function(){
        var notdata = $('#nottest .ntc').not('.w3-hide').children('span').get();
        var totaldata = '';
        for(var i = 0; i < notdata.length; i++){
            totaldata += notdata[i].innerHTML + '|';
        }
        alert(totaldata);
    });
	
	
	/* 버튼 누르면 모든 데이터 가져오는 함수*/
	$('#sbtn').click(function(){
		var getIng = $('.sList').not('.w3-hide').children('span').get();
		var SelIng = '';
		
		for(var i = 0; i < getIng.length; i++){
			SelIng += getIng[i].innerHTML + '|';
		} 
		alert(SelIng);
				
		$ .ajax( {
			url : "/fri/hyunuk/selected.fri",
			type : "POST",
			data : {"SelIng" : SelIng, "Situat" : Situat, "Category" : Category},
			traditional : true,
			dataType : "JSON",
		}).done(function(data) {
			var setlist = ""
			arr = [];
			var total = data.sLIST.length;
			
			$('#total').text(total);
			console.log('total@@@@@@@@@@@@@@@ : '+total);
			console.log(data.sLIST.length);
			for(var i = 0; i < data.sLIST.length; i++){
				arr[i] = data.sLIST[i];
				setlist = setlist + "<div id=" + arr[i].bno +" style='width: 402px;height: 520px;border: 1px solid;float: left;margin: 20px;'><a href= '/fri/juhyun/recipe/resipiPage.fri'class='img_thumb' style='width: 400px; height: 400px;'><br><img src= "+arr[i].tdir + "><br><center><span>" + arr[i].title + "</span><br><span>" +'작성자 : ' + arr[i].id + "</span><br><span>" + arr[i].time +'분'+ "</span><br><span>"+'좋아요 : '+ arr[i].lcount + "</span></a></center></div>";
				$('.sort_area').html(setlist);
			}
			
				
			console.log(arr[0].bno);
			console.log(arr[0]);						
			
		}).fail(function(err) {
		   alert(err);
		});
	});
	
	
	$('.chksituat').change(function SituatArr(){
   		Situat = [];     // 상황 배열
		
	    $("input[name='situat']:checked").each(function(i) {
	    	Situat.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
	    });

		alert(Situat);
		
		var situdat = {"Situat" : Situat}; 
		
		/*$ .ajax( {
			url : "/fri/hyunuk/situat.fri",
			type : "POST",
			data : situdat,
			traditional : true,
			dataType : "JSON",
		}).done(function(data) {
		   if (data) {
		      alert("성공하셨습니다.");
		   } else {
		      console.log("실패하셨습니다.");
		   }
		}).fail(function(err) {
		   console.log(err);
		});*/
	});
	
	$('.chkcategory').change(function CategoryArr(){
		Category = [];   // 카테고리 배열
		
	    $("input[name='category']:checked").each(function(i) {
	    	Category.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
	    });

		alert(Category);
		
		var CATdata = {"Category" : Category}; 
		/*
		$ .ajax( {
			url : "/fri/hyunuk/category.fri",
			type : "POST",
			data : CATdata,
			traditional : true,
			dataType : "JSON",
		}).done(function(data) {
		   if (data) {
		      alert("성공하셨습니다.");
		   } else {
		      console.log("실패하셨습니다.");
		   }
		}).fail(function(err) {
		   console.log(err);
		});*/
	});
});