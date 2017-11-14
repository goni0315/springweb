<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

/*  
 애니메이션을 구현하는 기술 3가지
 1. 스크립트 절차를 직접 제어하는 애니메이션
 2. jquery와 같은 라이브러리를 이용한 애니메이션
 3. css3 애니메이션 기능을 이용한 애니메이션 
 */
 //1. 스크립트 절차를 직접 제어하는 애니메이션
 function animate(target, to){
	var timer = setInterval(function(){			
			var width = parseInt(item.style.width);
			item.style.width = (width+1)+"px";			
			if(width > to)			
				clearInterval(timer);			
		}, 17);	
}
 
 
 window.addEventListener("load",function(e) {
	var widthButton = document.querySelector("#ex1-tool");
	var item = document.querySelector("#ex1-box .item1");
	
	widthButton.onclick = function(e){
	/* 	
		//2. 애니메이션을 animate함수로 리팩토링
		animate(item, 200);
		
		
		
		//1. 개별적으로 작성한 애니메이션
		if(timer == null)
		timer = setInterval(function(){
			
			
			var width = parseInt(item.style.width);
			item.style.width = (width+1)+"px";
			
			if(width > 200)			
				clearInterval(timer);
			
			
			
		}, 17); */
		
		//css에 추가된 변화 속성
		item.style.transform
		
	}
		
});

//jquery 와 같은 라이브러리를 이용한 애니메이션
$(function() {
	var widthButton = $("#ex1-tool input[value='너비늘리기']");
	var item = $("#ex1-box .item1");
	
	widthButton.click(function(e){
		
			//1. 한번에변화를 주는 방법 from --> to
			//item.css("width","200px");
			//item.css({
			//width:"200px"
			//});
			
			//2. 기간을 두고 점진적으로 변화를 주는 방법
			item.animate({
				width:"200px"
				
			}, 2000, "linear", function(){
				alert("야호");
				
			});
		
	});
		
});

</script>
<style type="text/css">
.item{
width:100px;
height:100px;
background: yellow;
transition: 1s;
transition-property: width, opacity;


}


.item1:hover{
width:200px;

}
</style>
</head>
<body>
<!--1. 너비를 변경하는 애니메이션  -->
<div id="ex1-tool">
<input type="button" value="너비늘리기">
</div>
<div id="ex1-box" style="width: 500px; height: 300px; background: gray;">
	<div class="item"> 	
	</div>
	<div class="item"> 	
	</div>
	<div class="item"> 	
	</div>
	<div class="item"> 	
	</div>
	<div class="item"> 	
	</div>
	<div class="item"> 	
	</div>
</div>



</body>
</html>