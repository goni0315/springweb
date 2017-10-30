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
				   removeNodeButton.click(function() {
				     /*  // 1. 내정된 노드를 지우기
				      if(container.hasChildNodes()){
				         container.removeChild(container.lastChild);
				      } */
				      
				      //$("#node-container *:last-child").remove();
			      
				      //$("#node-container").children().last().remove();
				     // $("#node-container img").eq(0).attr("src","data:image/jpeg;base64,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")
				     //$("#node-container img").get(0).src="data:image/jpeg;base64,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" 
				     $("#node-container img").each(function(index){
				    	 $(this).remove();
				    	 
				     })
				     
				     //eq(0) 인덱스개념으로 선택함
				      // 2. 선택된 노드를 지우기
				      
				      
				   });
				});
			
			
			
			
			
			
			
			
			
			//!--노드 바꾸기 예제 -->
			window.addEventListener("load", function() {
			   var swapNodeButton = document.querySelector("#swap-node-button");
			   var container = document.querySelector("#swap-node-container");   
			   
			   swapNodeButton.onclick = function() {
			      var nodes = container.getElementsByTagName("img");
			      
			      var node1 = nodes[0];
			      var node2 = nodes[1];
			      var node3 = nodes[2];
			      
			      /* var node1 = container.firstElementChild;
			      var node3 = container.lastElementChild; */
			      //var oldNode = container.replaceChild(node1, node3);
			      //container.insertBefore(node3, container.firstChild);
			      var oldNode = container.replaceChild(node2, node3);
			      container.insertBefore(node3, container.firstElementChild);
			   };
			}); 
</script>
</head>
<body>
<!--노드 바꾸기 예제 -->
   <input id="swap-node-button" type="button" value="노드 바꾸기" />
   <div id="swap-node-container">
      <img src="../images/answeris.png" /><img src="../images/w3c.png" /><img
         src="../images/microsoft.png" />
   </div>
   <hr />














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
