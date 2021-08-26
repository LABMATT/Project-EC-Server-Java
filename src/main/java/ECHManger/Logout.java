package ECHManger;

import org.java_websocket.WebSocket;
import sqlManger.PStatment;

import java.sql.SQLException;

public class Logout {
    public Logout(WebSocket conn) {

        try {

            new PStatment().updateQuary("DELETE FROM active WHERE (`socketid` = '"+conn.getRemoteSocketAddress()+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
