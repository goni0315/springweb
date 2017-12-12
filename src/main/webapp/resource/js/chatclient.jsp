<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="id" value="test" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div style="border:1px solid #e9e9e9;
            background : orange;
            width:300px;
            height:500px;
            padding:10px;
            ">
      <div style="">
         <input id="con-button" type="button" value="연결"/>
      </div>
      <div id="chat-list" 
            style="border:1px solid #e9e9e9;
                  height:430px;
                  background:white;">
         
      </div>
      <div style="border:1px solid #e9e9e9; 
               height:50px;
               background:white;
               ">
         <textarea id="chat-text" style="width:100%;height:90%;"></textarea>      
         <input id="send-button" type="button" value="send"/>
      </div>
   </div>
</body>

<script type="text/javascript">
   window.addEventListener("load",function(event){
      //var chatList=null;
      var id = '${id}';
      var chatList = document.querySelector("#chat-list");
      var chatText = document.querySelector("#chat-text");
      var sendButton = document.querySelector("#send-button");
      var socket = null;
      var conButton = document.querySelector("#con-button");
      
      
      sendButton.addEventListener("click",function(e){
    	  var data = {id:id, text:chatText.value};
         socket.send(JSON.stringfy(data));
         //alert(chatText.value);
         
      });
      
      
      conButton.addEventListener("click",function(e){
         socket = new WebSocket("ws://211.238.142.72:8080/SpringMVC/resource/chat-server");
         //socket = new WebSocket("ws://localhost/SpringWeb/resource/chat-server");
         socket.onopen = function(){
            //alert("연결되었습니다.");   
            var li = document.createElement("li");
            li.textContent="접속되었습니다.";
            chatList.appendChild(li);         
         };//소켓이 연결될때 한번
         socket.onmessage= function(evt){
            var li = document.createElement("li");
			var data = JSON.parse(evt.data);
            li.textContent=evt.data;
            chatList.appendChild(li);
            
         };//소켓을 이용하여 챗팅을 할때 여러번
         socket.onclose = function(){
            var li = document.createElement("li");
            li.textContent = "서버와의 연결이 종료되었습니다.";
            chatLsit.appendChild(li);
            
         };//소켓을 종료시킬때 한번
      }); 
   });

</script>
</html>