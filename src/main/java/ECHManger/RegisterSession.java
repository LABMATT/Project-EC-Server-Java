package ECHManger;

import org.java_websocket.WebSocket;
import sqlManger.PStatment;

import java.sql.SQLException;

public class RegisterSession {

    public RegisterSession(String sufix, WebSocket conn) {
        System.out.println("SEtingup user");

        try {
            new PStatment().updateQuary("UPDATE active SET socketid='" + conn.getRemoteSocketAddress() + "' WHERE echid= '"+ sufix +"';");
            System.out.println("registered new scoket: " + sufix);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
