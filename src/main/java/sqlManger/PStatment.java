package sqlManger;

import java.sql.*;

public class PStatment {

    // Providing a statement, connects to server then returns the result and closes the connection.
    public ResultSet getQury(String statement) throws SQLException {

        final String db = "ech";
        final String host = "localhost";
        final String username = "root";
        final String password = "";
        final String port = "3306";


        Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statement);
        //con.close();

        return rs;
    }

    public void close(Connection c) throws SQLException {
        c.close();
    }

    // Providing a statement, connects to server then returns the result and closes the connection.
    public boolean updateQuary(String statement) throws SQLException {

        final String db = "ech";
        final String host = "localhost";
        final String username = "root";
        final String password = "";
        final String port = "3306";


        Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);

        Statement stmt = con.createStatement();
        boolean rs = stmt.execute(statement);
        con.close();

        return rs;
    }
}
