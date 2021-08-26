package ECHManger;

import org.java_websocket.WebSocket;
import sqlManger.PStatment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetName {
    public GetName(WebSocket connection) {
        System.out.println("getting name user");

        try {
            PStatment pStatment = new PStatment();

            ResultSet rs = pStatment.getQury("SELECT username FROM active WHERE socketid=' " + connection.getRemoteSocketAddress() + "';");
            System.out.println(" User name is: " + rs.getString("username"));
            pStatment.close(pStatment);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
