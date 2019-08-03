package main.java.sql;

import java.sql.*;
import java.time.LocalDateTime;

public class DataBaseLogger {
    private static Statement stmt;

    public static void main(String[] args) throws SQLException {
        getStmt ();


        createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOG (" +
                " id INTEGER not NULL," +
                " userName VARCHAR(255)," +
                " userMessage VARCHAR(255)," +
                " date TIMESTAMP, " +
                " PRIMARY KEY ( id ))");


//        addDataBase ( "INSERT INTO Tablelog VALUES (100, 'Zara', 'Ali', 18)" );
//        addDataBase ( "INSERT INTO Tablelog VALUES (101, 'Para', 'Ali', 19)" );
//        addDataBase ( "INSERT INTO Tablelog VALUES (102, 'Vara', 'Ali', 18)" );
//        addDataBase ( "INSERT INTO Tablelog VALUES (103, 'Sara', 'Ali', 17)" );
          addDataBase ( "Tablelog", 100, "Zara", "Adfffffli");
//        addDataBase ( "Tablelog", 101, "Fara", "Adfffffli");
//        addDataBase ( "Tablelog", 102, "Vara", "Adfffffli");
//        addDataBase ( "Tablelog", 103, "Sara", "Adfffffli");
//
//        addDataBase ( "Tablelog", 105, "Fara", "Adfffffli", 16);

//        updataDb("Registration","last","lik",104);

//        deleteDataUS("REGISTRATION", 106);

        readTableDB( "id", "userName", "userMessage", "Tablelog");
        System.out.print("ID: " + dateTime());
        closeDataBase ();
    }

    private static Connection getDbConn( ) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange";
        try {
            Class.forName ( JDBC_DRIVER );
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace ( );
        }
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection ( DB_URL );
        } catch (SQLException e1) {
            e1.printStackTrace ( );
        }
        return dbConn;
    }

    private static Statement getStmt() throws SQLException {
        stmt = getDbConn ( ).createStatement ( );
        return stmt;
    }

    private static void createTableDb(String sql) throws SQLException {
            stmt.executeUpdate ( sql );
            System.out.println("Created table in given database...");
    }

    private static void addDataBase(String nameTable, Integer idSet, String userNameSet, String userMessageSet) throws SQLException {
        String sql = "INSERT INTO " + nameTable + " VALUES " + "(" + idSet + ", " + "'"+ userNameSet + "'" + ", " + "'" + userMessageSet + "'" + ", " + "'" + dateTime() + "'" + ")";
        stmt.executeUpdate ( sql );
        System.out.println ( "Inserted records into the table..." );
        getDbConn ( ).commit ( );
        getDbConn ( ).rollback ( );
    }

    private static void updataDb(String nameTable, String userNameSet, String updataString,  Integer idSet) throws SQLException {
        String sql = "UPDATE " + nameTable +" SET " + userNameSet + " = "+ "'" +updataString + "' WHERE id"  + " in (" + idSet + ")";
            stmt.executeUpdate(sql);
            System.out.println ( "Update DataBase into the table..." );
    }

    private static Timestamp dateTime( ){
        /*
        The TIMESTAMP data type is used for values that contain both date and time parts. TIMESTAMP has a range of '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC
         */
        Timestamp dateTimeStr = new Timestamp(System.currentTimeMillis());
        //or new Timestamp(new Date.getTime());
        return dateTimeStr;
    }

    private static void closeDataBase( ) {
        try {
            if (getDbConn ( ) != null) getDbConn ( ).close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
        try {
            if (stmt != null) stmt.close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
    }

    private static void readTableDB(String idSet, String userNameSet, String userMessageSet, String nameTable ) throws SQLException {
        String sql = "SELECT " + idSet + ", " + userNameSet + ", " + userMessageSet + ", " + dateTime () + " " + "FROM " + nameTable;
        ResultSet rs = getResultSet(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id=rs.getInt("id");
                String userName=rs.getString("userName");
                String userMessage=rs.getString("userMessage");
                String date=rs.getString("date");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", date: " + date);
                System.out.print(", userName: " + userName);
                System.out.println(", userMessage: " + userMessage);
            }
    }

    private static ResultSet getResultSet(String sql) throws SQLException {
            ResultSet rs = null;
            rs = stmt.executeQuery (sql);
            return rs;
    }

    private static void deleteDataUS(String nameTable, Integer idSet) throws SQLException {
        String sql ="DELETE FROM " + nameTable + " WHERE ID = " + idSet ;
            stmt.executeUpdate(sql);
    }

}




