package com.my.access;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
   // static HashMap<String,Session> sessionUsers_list=new HashMap();
     static NotifyJDBC NJ = new NotifyJDBC();
     
    /**
     * �� ������ ���ӵǸ� ��������Ʈ�� ������ �ִ´�.
     * @param userSession �� ���� ����
     */
    @OnOpen
    public void handleOpen(Session userSession){
        System.out.println("ȣ��");
        System.out.println(userSession);
        sessionUsers.add(userSession);
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
    	//String username = (String)userSession.getUserProperties().get("username");
    	System.out.println("message==================>"+message);


        //username�� ������ ��ü���� �޽����� ������.
    	//1.client_ok, id , ������ȣ
    	//2.init , id , ������ȣ
    	//3.key , id , ������ȣ

    	s_array=message.split(",");
    	System.out.println(s_array[0]);
    	System.out.println(s_array[1]);
    	System.out.println(s_array[2]);
//    	if(username == null){
//            userSession.getUserProperties().put("username", s_array[1]);
//            System.out.println(userSession.getUserProperties());
//            System.out.println(userSession);
//         //   sessionUsers_list.put(s_array[2], userSession);
//        }
    	if(s_array[0].equals("client_ok")){
    		System.out.println("��������");
    		//Verify vf= new Verify("id");
    		Verify vf= new Verify(s_array[1]);
			vf.VerifyUser();
			//System.out.println(sessionUsers.get(s_array[2]));
//			sessionUsers_list.get(s_array[2]).getBasicRemote().sendText("test");
          Iterator<Session> iterator = sessionUsers.iterator();

			while(iterator.hasNext()){
    			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
    	        iterator.next().getBasicRemote().sendText("open the door");
    	        System.out.println("������");
    	     }
    	}else if(s_array[0].equals("init")){
           if(NJ.Socketfilter(s_array[1])=="success" ){
               NJ.SocketRegi(s_array[1],s_array[2]);

           }else{
        	   System.out.println("�̹� ��ϵ� �����");
           }
           Iterator<Session> iterator = sessionUsers.iterator();
   		while(iterator.hasNext()){
   			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
   	        iterator.next().getBasicRemote().sendText(s_array[1]+"DB REGISTER");
  	     }	
    	}else if(s_array[0].equals("client_fail")){
    	    Iterator<Session> iterator = sessionUsers.iterator();
       		while(iterator.hasNext()){
       			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
       	        iterator.next().getBasicRemote().sendText("failed");
      	     }	
    	}
    	else{
			//sessionUsers_list.get(s_array[2]).getBasicRemote().sendText("test");

    		Iterator<Session> iterator = sessionUsers.iterator();
    		while(iterator.hasNext()){
    			//��������� ���� key,id�� Ŭ���̾�Ʈ�� ����
    	        iterator.next().getBasicRemote().sendText(message);
    	        System.out.println("Ŭ���̾�Ʈ�� ����");
    	     }	
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
}

