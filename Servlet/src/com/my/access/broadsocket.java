package com.my.access;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
 

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
 
@ServerEndpoint("/broadsocket")
public class broadsocket {
    //���� ���� ����Ʈ
    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
     
    /**
     * �� ������ ���ӵǸ� ��������Ʈ�� ������ �ִ´�.
     * @param userSession �� ���� ����
     */
    @OnOpen
    public void handleOpen(Session userSession){
        sessionUsers.add(userSession);
        System.out.println("ȣ��");
    }
    /**
     * �� �������κ��� �޽����� ���� ȣ���Ѵ�.
     * @param message �޽���
     * @param userSession
     * @throws IOException
     */
    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException{
        String username = (String)userSession.getUserProperties().get("username");
        //���� ������Ƽ�� username�� ������ username�� �����ϰ� �ش� ���������� �޽����� ������.(json �����̴�.)
        //���� �޽����� username����
        if(username == null){
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText(buildJsonData("System", "you are now connected as " + message));
            return;
        }
        //username�� ������ ��ü���� �޽����� ������.
        Iterator<Session> iterator = sessionUsers.iterator();
        while(iterator.hasNext()){
            iterator.next().getBasicRemote().sendText(buildJsonData(username,message));
        }
    }
    /**
     * �������� ������ �ش� ������ ��������Ʈ���� ����.
     * @param userSession
     */
    @OnClose
    public void handleClose(Session userSession){
        sessionUsers.remove(userSession);
    }
    /**
     * jsonŸ���� �޽��� �����
     * @param username
     * @param message
     * @return
     */
    public String buildJsonData(String username,String message){
    
        return username+message;
    }
}

