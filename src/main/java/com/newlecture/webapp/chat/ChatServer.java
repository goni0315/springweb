package com.newlecture.webapp.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/resource/chat-server")
public class ChatServer {
   //POJO : Plain Object Old Java Object
   /*값을 식별하는 키의 차이점
    * Map / List/ Set
    * set은 값=키
    * list는 값의 입력 순서= 키
    * Map 키 설정 가능
    * 
    */
   /*session은 접속자 마다 별도의 스레드가 생성됨.
    * 스레드에 잠금장치를 달아줘야해. 다른사람이 못들어오게
    * 
    * */
   
   //접속한 것을 누적, 메시지를 모두에게 쏴줘
   private static Set<Session> sessionAll = Collections.synchronizedSet(new HashSet<Session>());//문앞에 잠금장치를 달아줘
   
   @OnOpen
   public void onOpen(Session session, EndpointConfig config) {
      sessionAll.add(session);
      System.out.println("누가 누가 접속했을까요?");
   }
   
   @OnMessage
   public void onTextMessage(Session session, String data) throws IOException {
      for(Session s: sessionAll)
         s.getBasicRemote().sendText(data);
      System.out.println(data);
   }
   @OnClose
   public void onClose(Session session) throws IOException {
      sessionAll.remove(session);
      for(Session s : sessionAll)
         s.getBasicRemote().sendText("누군가가 접속을 끊었습니다.");
   }
   
}