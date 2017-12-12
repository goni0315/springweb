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
   /*���� �ĺ��ϴ� Ű�� ������
    * Map / List/ Set
    * set�� ��=Ű
    * list�� ���� �Է� ����= Ű
    * Map Ű ���� ����
    * 
    */
   /*session�� ������ ���� ������ �����尡 ������.
    * �����忡 �����ġ�� �޾������. �ٸ������ ��������
    * 
    * */
   
   //������ ���� ����, �޽����� ��ο��� ����
   private static Set<Session> sessionAll = Collections.synchronizedSet(new HashSet<Session>());//���տ� �����ġ�� �޾���
   
   @OnOpen
   public void onOpen(Session session, EndpointConfig config) {
      sessionAll.add(session);
      System.out.println("���� ���� �����������?");
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
         s.getBasicRemote().sendText("�������� ������ �������ϴ�.");
   }
   
}