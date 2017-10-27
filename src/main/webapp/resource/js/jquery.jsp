<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	//window.addEventListener("load", function(){
		$(function(){
		// dom 객체들
		//var _chButton = document.getElementById("ch-button");
		
		//jquery 객체로 바꾸는 방법 1: jquery 함수 이용하기
		//var chButton = jQuery(_chButton);
		
		//jquery 객체로 바꾸는 방법 2: $함수 이용하기
		//var chButton = $(_chButton);		
		
		//jquery 객체로 바꾸는 방법 3: css selector 이용하기
		var chButton = $("#ch-button");
		
		
		//이벤트 바인딩 두 가지 옵션 : 첫번째 범용 이벤트 바인딩 함수 on()
	/* 	chButton.on("click", function(){
	
		alert("aa");
		})		
	}); */
	
	//이벤트 바인딩 두 가지 옵션 : 두번째 특수 이벤트 바인딩 함수 click keydown
	
	 	chButton.click(function(){
	 		
	 		//여러개의 스타일을 적용하는 경우 1
	 		/* $("#p").css("background","red");	
	 		$("#p").css("background","red"); */
	 		
	 		$("#p").css({
	 		
	 			background : "red",
	 			"font-size" : "23px"
	 			
	 			
	 		})
	
		})		
	}); 

		   <!--------------속성변경예제 ----------------->
			$(function(){
			   
			   var chImgButton = $("#ch-img-button");
			   var container = $("#img-container");
			   var img = $("img");
			   var imgSrc = $("#img-src");
			   

			      chImgButton.click(function(){
			    	img.attr("src", imgSrc.val());  
			      
			      })
			      });
			
			//---------자식노드변경예제 ----------->
			$(function() {
			   var chNodeButton = $("#ch-node-button");
			   var container = $("#ch-node-container");   
			   chNodeButton.click(function() {
			      
				   //1. 텍스트 노드 추가
				   //container.textContent = "<h1>testtestset</h1>"
				 //  container.text("<h1>testtestset</h1>");
				   //text는 쓰는 그대로 나옴
				   
				   //2. 엘리먼트 노드 추가				   
			      //container.innerHTML = "<h1>testtestset</h1>"
			      container.html("<h1>testtestset</h1>");
			      //.html 은 html태그가 적용됨
			   });
			});
			<!--------텍스트 노드 추가 예제--------->
			$(function() {
				   var addTextNodeButton = $("#add-text-node-button");
				   var addImgNodeButton = $("#add-img-node-button");
				   var removeNodeButton = $("#remove-node-button");
				   var container = $("#node-container");   
				   
				   var remove = function(){
				      //container.removeChild(e.target);
				   
				   } 
				   var idx = 0;
				   addTextNodeButton.click(function() {
				      //1.TextNode 생성
				      var span = $('<span />'); // span을 만듦 <>이 없으면 찾음
				      var txt = '안녕하세여'+ idx++;
				      //2. container(부모) 얻기
				      //3. 부모에 자식을 추가
				      span.append(txt);
				      container.append(span);
				      
				      span.click(remove);
				      
				   });
				   addImgNodeButton.click(function() {
				       //How to 1(성능에 좋음)
				      //1 Element 생성
				      // 이 방법은 넘나 dom사용하는 스타일로 구현
				    //  var img = $('<img />');
				    //  img.attr("src","http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D")
				      //2. container(부모) 얻기
				      //3. 부모에 자식을 추가
				     // container.append(img);
				    //  img.click(remove);
				      
				      //jquery  style
				      $("<img src = 'http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile25.uf.tistory.com/image/2239B038579139CF067E3D'/>").appendTo(container /*부모를 넣어줌*/).click(remove);
				      //appendTo() 부모를 지정 return 값이 jquery면 .으로 계속 붙여서 씀?
				      
				      //How to 2 (성능에 좋지 않지만 편리함.-> 한두개 넣는 상황의 성능에 영향을 주지 않을 거라면 이를 사용)
				      //container.innerHTML += '<img onClick="remove()" src="http://t1.daumcdn.net/thumb/R1024x0/?fname=http://cfile1.uf.tistory.com/image/2134514F579B4C7D165741" />';
				      
				      
				   });
				   removeNodeButton.onclick(function() {
				      // 1. 내정된 노드를 지우기
				      if(container.hasChildNodes()){
				         container.removeChild(container.lastChild);
				      }
				      // 2. 선택된 노드를 지우기
				      
				   });
				});
		  
</script>
</head>
<body>
<!--텍스트 노드 추가 예제 -->
   <input id="add-text-node-button" type="button" value="텍스트노드 추가" />
   <input id="add-img-node-button" type="button" value="이미지노드 추가" />
   <input id="remove-node-button" type="button" value="노드 삭제" />
   <div id="node-container"></div>
   <hr />
<!--자식노드변경예제 -->
   <input id="ch-node-button" type="button" value="자식노드 변경" />
   <div id="ch-node-container">hello</div>
   <hr />

   <!--------------속성변경예제 ----------------->
   <input type="text" id="img-src" />
   <input id="ch-img-button" type="button" value="이미지 변경" />
   <div id="img-container">
      <img src="http://i.huffpost.com/gen/5524070/thumbs/o-THE-570.jpg?3" />
   </div>
   <hr />

<!-- ----------------------노드 순회 예제 ---------------------------------->
	<input id="ch-button" type="button" value="배경색 변경" >
	<div>
		<div>1</div>
		<div id="p">
			<div>2</div>
		</div>
		<div>3</div>

	</div>

	<hr />
	
	
	
	
</body>
</html>
