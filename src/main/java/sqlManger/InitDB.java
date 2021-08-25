package sqlManger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InitDB {

    public void check() {

        final String db = "ech";
        final String host = "localhost";
        final String username = "root";
        final String password = "";
        final String port = "3306";

        String error = "";

        System.out.println("Checking DB Connection.");

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);
            con.close();

            System.out.println("Database Was Found.");

        } catch (Exception e)
        {
            System.out.println("Error Connecting to SQL or maybe database did not exist.");
            System.out.println("Error in sql: " + e.getMessage());
            error = e.getMessage();
        }

        if(error.equals("Unknown database 'ech'"))
        {
            System.out.println("Attempting to create database.");

            try {

                Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/", username, password);
                Statement stmt = con.createStatement();

                // Try make the database.
                boolean rs = stmt.execute("CREATE DATABASE ech;");

                if(rs)
                    System.out.println("Database Was Created.");
                else System.out.println("Error Creating Database.");

                con.close();


            } catch (Exception e)
            {
                System.out.println("Error Creating Database.");
                System.out.println("Error in sql: " + e.getMessage());
            }
        }


        // Create ther users table for the ECH system.
        try {
            System.out.println("Attempting to create user table.");
            Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);
            Statement stmt = con.createStatement();
            boolean rs = stmt.execute("CREATE TABLE `users` (" + "  `id` INT NOT NULL AUTO_INCREMENT," + "  `username` VARCHAR(45) NOT NULL," + "  `password` VARCHAR(45) NOT NULL," + "  `project` VARCHAR(200) NULL," + "  `admin` TINYINT NOT NULL," + "  PRIMARY KEY (`id`));");
            con.close();

            if(rs)
                System.out.println("Users Table Was Created.");
            else System.out.println("Table Already Exists.");


        } catch (Exception e)
        {
            System.out.println("Error Creating Database.");
            System.out.println("Error in sql: " + e.getMessage());
        }

        // Create the active table for active users for ECH.
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);
            Statement stmt = con.createStatement();
            boolean rs = stmt.execute("CREATE TABLE `ech`.`active` (" + "  `id` INT NOT NULL AUTO_INCREMENT," + "  `echid` VARCHAR(45) NOT NULL," + "  `socketid` VARCHAR(200) NOT NULL," + "  `username` VARCHAR(45) NOT NULL," + "  `checkin` VARCHAR(45) NULL," + "  `admin` TINYINT NULL," + "  PRIMARY KEY (`id`));");
            con.close();

            if(rs)
                System.out.println("Active Table Was Created.");
            else System.out.println("Active Table Already Exists.");

        } catch (Exception e)
        {
            System.out.println("Error Creating Database.");
            System.out.println("Error in sql: " + e.getMessage());
        }


        // Create the ProjectINFO table for active users for ECH.
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql:// " + host + " : " + port + "/ " + db + " ", username, password);
            Statement stmt = con.createStatement();
            boolean rs = stmt.execute("CREATE TABLE `ech`.`projectinfo` (" + "  `idprojectinfo` INT NOT NULL AUTO_INCREMENT," + "  `pname` VARCHAR(45) NOT NULL," + "  `pdatecreated` VARCHAR(45) NULL," + "  `pdateupdated` VARCHAR(45) NULL," + "  `pdormantdir` VARCHAR(45) NULL," + "  `pactivedir` VARCHAR(45) NULL," + "   `isDisabled` TINYINT NULL, " + "  PRIMARY KEY (`idprojectinfo`));");
            con.close();

            if(rs)
                System.out.println("ProjectINFO Table Was Created.");
            else System.out.println("ProjectINFO Already Exists.");

        } catch (Exception e)
        {
            System.out.println("Error Creating Database.");
            System.out.println("Error in sql: " + e.getMessage());
        }

    }
}
