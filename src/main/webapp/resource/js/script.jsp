<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<!-- --------------------------------------template 노드 복제 예제 --------------------------------------------->
window.addEventListener("load", function() {
	   var cloneButton = document.querySelector("#ex2-clone input[value='단순복제']");
	   var ajaxButton = document.querySelector("#ex2-clone input[value='Ajax요청']");
	   var ajaxContainer = document.querySelector("#ajaxContainer");
	   var tbody = document.querySelector("#ex2-clone tbody");
		var template = document.querySelector("#ex2-clone template");   	   
	   
		var data = [{id:"1", title:"2", writerId:"3"},{id :"4", title:"5", writerId:"6"},{id : "7", title:"8", writerId:"9"}];
		   
		   ajaxButton.onclick = function(e){
			   
			var xhr = new XMLHttpRequest();
		   
		  /*  //동기방식으로 요청을 함 속도가 느림
				xhr.open("GET", "../../customer/notice-ajax", false); 
				xhr.send();
				data = eval(xhr.responseText); */
				
				
		     //비동기방식으로 요청을 
	/* 	     xhr.onreadystatechange = function(e){
				
					if(xhr.readyState == 4)					
						data = eval(xhr.responseText); 
				}; */		
					
				xhr.onload = function(){
					data = JSON.parse(xhr.responseText);
					//2.ajax icon 제거	
					ajaxContainer.removeChild(ajaxContainer.lastChild);
				};
				
				xhr.onerror = function(e){
					
					alert("오류발생");
				}
				xhr.open("GET", "../../customer/notice-ajax", true); 
				xhr.send();
				//1.ajax icon 추가
				  var img = document.createElement("img");
	                img.src ="../images/ajax-loader.gif";
	                ajaxContainer.appendChild(img);
				
	   
		   
		   }
		   
		   
	   cloneButton.onclick = function(e){
	/*  tempalte가 가지고 있는 내용은 content가 둘러싸고 있어야 하기땀시 content가 있으면 tempalte가 지원이 된다는 무 ㅓ그런이야기? */
	   if('content' in template){
					   
		  /*  //tempalte의 content에 값을 설정
		   var tds = template.content.querySelectorAll("td");
			
			tds[0].appendChild(document.createTextNode("1"));
			tds[1].textContent = "test title";
			tds[2].textContent = "newlec";
	   					
			// 값이 설정된 content를 복제
			var clone = document.importNode(template.content, true); */
			
			 //tempalte의 content에 값을 설정
			 
			 for(var i = 0; i<data.length; i++){
			   var clone = document.importNode(template.content, true);
			   var tds = clone.querySelectorAll("td");
				
				tds[0].appendChild(document.createTextNode(data[i].id));
				tds[1].textContent = data[i].title;
				tds[2].textContent = data[i].writerId;
			 				
				// 값이 설정된 content를 복제			
						
			// 복제된 tr을 노드 트리에 추가	
	  	 	tbody.appendChild(clone);
			 }
	   }
		
	   };
	
	});	
</script>
</head>
<body>

	<div id="ex2-clone">

		<div id="ajaxContainer">
			<input type="button" value="단순복제" /> 
			<input type="button" value="Ajax요청" />
		</div>
		<div id="clone-container">

			<table border="1">
				<thead>
					<tr>
						<td>코드</td>
						<td>코드</td>
						<td>제목</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input name="id" type="radio" value="1" /></td>
						<td>1</td>
						<td>하자고 하길 잘했어</td>
					</tr>
				</tbody>
			</table>

			<!--  template는 보여지지않고 안에 tr이 위에 테이블에서 없는경우 template에 있는 tr을 쓰겠다는 뭐 그런 지원되지 않는 브라우저는 있음-->
			<template>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</template>
		</div>
	</div>
	<hr />
</body>
</html>

<!-- type of = 데이터형식을 출력해줌.
제이슨사용시 표기법준수. 숫자에는 ""안넣음. -->