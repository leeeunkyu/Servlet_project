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
    //유저 집합 리스트
    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
   // static HashMap<String,Session> sessionUsers_list=new HashMap();
     static NotifyJDBC NJ = new NotifyJDBC();
     
    /**
     * 웹 소켓이 접속되면 유저리스트에 세션을 넣는다.
     * @param userSession 웹 소켓 세션
     */
    @OnOpen
    public void handleOpen(Session userSession){
        System.out.println("호출");
        System.out.println(userSession);
        sessionUsers.add(userSession);
    }
    /**
     * 웹 소켓으로부터 메시지가 오면 호출한다.
     * @param message 메시지
     * @param userSession
     * @throws IOException
     */
    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException{
    	String[] s_array = new String[3];
    	//String username = (String)userSession.getUserProperties().get("username");
    	System.out.println("message==================>"+message);


        //username이 있으면 전체에게 메시지를 보낸다.
    	//1.client_ok, id , 고유번호
    	//2.init , id , 고유번호
    	//3.key , id , 고유번호

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
    		System.out.println("승인했음");
    		//Verify vf= new Verify("id");
    		Verify vf= new Verify(s_array[1]);
			vf.VerifyUser();
			//System.out.println(sessionUsers.get(s_array[2]));
//			sessionUsers_list.get(s_array[2]).getBasicRemote().sendText("test");
          Iterator<Session> iterator = sessionUsers.iterator();

			while(iterator.hasNext()){
    			//도어락에서 받은 key,id값 클라이언트로 전송
    	        iterator.next().getBasicRemote().sendText("open the door");
    	        System.out.println("문열림");
    	     }
    	}else if(s_array[0].equals("init")){
           if(NJ.Socketfilter(s_array[1])=="success" ){
               NJ.SocketRegi(s_array[1],s_array[2]);

           }else{
        	   System.out.println("이미 등록된 사용자");
           }
           Iterator<Session> iterator = sessionUsers.iterator();
   		while(iterator.hasNext()){
   			//도어락에서 받은 key,id값 클라이언트로 전송
   	        iterator.next().getBasicRemote().sendText(s_array[1]+"DB REGISTER");
  	     }	
    	}else if(s_array[0].equals("client_fail")){
    	    Iterator<Session> iterator = sessionUsers.iterator();
       		while(iterator.hasNext()){
       			//도어락에서 받은 key,id값 클라이언트로 전송
       	        iterator.next().getBasicRemote().sendText("failed");
      	     }	
    	}
    	else{
			//sessionUsers_list.get(s_array[2]).getBasicRemote().sendText("test");

    		Iterator<Session> iterator = sessionUsers.iterator();
    		while(iterator.hasNext()){
    			//도어락에서 받은 key,id값 클라이언트로 전송
    	        iterator.next().getBasicRemote().sendText(message);
    	        System.out.println("클라이언트로 전송");
    	     }	
    	}
    	    }
    /**
     * 웹소켓을 닫으면 해당 유저를 유저리스트에서 뺀다.
     * @param userSession
     */
    @OnClose
    public void handleClose(Session userSession){
        sessionUsers.remove(userSession);
    }
}

