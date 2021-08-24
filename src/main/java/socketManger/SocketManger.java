package socketManger;

//https://github.com/TooTallNate/Java-WebSocket/wiki#server-example
//https://github.com/TooTallNate/Java-WebSocket

import MessageManger.Message;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import sqlManger.PStatment;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.sql.SQLException;

public class SocketManger extends WebSocketServer {

    final PStatment pStatment = new PStatment();

    public SocketManger(InetSocketAddress address)
    {
        super(address);
    }


    // When a fresh socket is opened such as a page refresh or user join.
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        //broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
        System.out.println(handshake.getResourceDescriptor());

        try {
            pStatment.updateQuary("INSERT INTO active (`wsocketID`, `ulmid`) VALUES ('"+conn.getRemoteSocketAddress()+"', 'lmid');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // When a socket disconnets.
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);

        try {
            System.out.println(pStatment.updateQuary("DELETE FROM active WHERE (`wsocketID` = '"+conn.getRemoteSocketAddress()+"');"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        //System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
        System.out.println("ready for thread");

        Thread messagethread = new Thread( new Message(message, conn));
        messagethread.start();
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }
}