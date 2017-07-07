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
    	String[] s_array = new String[3];
//        String username = (String)userSession.getUserProperties().get("username");
//        //���� ������Ƽ�� username�� ������ username�� �����ϰ� �ش� ���������� �޽����� ������.(json �����̴�.)
//        //���� �޽����� username����
//        if(username == null){
//            userSession.getUserProperties().put("username", message);
//            userSession.getBasicRemote().sendText(buildJsonData("System", "you are now connected as " + message));
//            return;
//        }
        //username�� ������ ��ü���� �޽����� ������.
    	s_array=message.split(",");
    	System.out.println(s_array[0]);
    	System.out.println(s_array[1]);
    	System.out.println(s_array[2]);
    	if(s_array[0].equals("client_ok")){
    		Verify vf= new Verify("id");
    		//Verify vf= new Verify(s_array[1]);
			vf.VerifyUser();
			Iterator<Session> iterator = sessionUsers.iterator();
    		while(iterator.hasNext()){
    			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
    	        iterator.next().getBasicRemote().sendText(s_array[0]);
    	     }
    	}else{
    		Iterator<Session> iterator = sessionUsers.iterator();
    		while(iterator.hasNext()){
    			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
    	        iterator.next().getBasicRemote().sendText(message);
    	     }	
    	}
    	System.out.println(s_array[2]);
    }
    /**
     * �������� ������ �ش� ������ ��������Ʈ���� ����.
     * @param userSession
     */
    @OnClose
    public void handleClose(Session userSession){
        sessionUsers.remove(userSession);
    }
}

