package socketManger;

import jakarta.websocket.server.ServerEndpoint;

import javax.websocket.*;


@ServerEndpoint(value = "/echo")
public class ECEndpoint {

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("connected to: " + session.getId());
        session.addMessageHandler((MessageHandler.Whole<String>) System.out::println);
    }
/*
    @OnMessage
    public void onMessage(byte[] message) {
        //System.out.println("Message: " + message.toString());
    }

 */

    @OnClose
    public void onClose(Session session) {
        System.out.println("session closed: " + session.getId());

    }

    @OnError
    public void onError(Session session, Throwable throwable) {

    }



}