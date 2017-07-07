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
    //유저 집합 리스트
    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
     
    /**
     * 웹 소켓이 접속되면 유저리스트에 세션을 넣는다.
     * @param userSession 웹 소켓 세션
     */
    @OnOpen
    public void handleOpen(Session userSession){
        sessionUsers.add(userSession);
        System.out.println("호출");
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
//        String username = (String)userSession.getUserProperties().get("username");
//        //세션 프로퍼티에 username이 없으면 username을 선언하고 해당 세션을으로 메시지를 보낸다.(json 형식이다.)
//        //최초 메시지는 username설정
//        if(username == null){
//            userSession.getUserProperties().put("username", message);
//            userSession.getBasicRemote().sendText(buildJsonData("System", "you are now connected as " + message));
//            return;
//        }
        //username이 있으면 전체에게 메시지를 보낸다.
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
    			//도어락에서 받은 key,id값 클라이언트로 전송
    	        iterator.next().getBasicRemote().sendText(s_array[0]);
    	     }
    	}else{
    		Iterator<Session> iterator = sessionUsers.iterator();
    		while(iterator.hasNext()){
    			//도어락에서 받은 key,id값 클라이언트로 전송
    	        iterator.next().getBasicRemote().sendText(message);
    	     }	
    	}
    	System.out.println(s_array[2]);
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

