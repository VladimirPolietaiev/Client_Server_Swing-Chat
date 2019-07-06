package main.java.sql;

import java.sql.*;

public class DataBaseLogger {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        getDbConn ( );

        createTableDb("CREATE TABLE IF NOT EXISTS  REGISTRATION (id INTEGER not NULL," +
                " first VARCHAR(255)," +
                " last VARCHAR(255)," +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))");


//        addDataBase ( "INSERT INTO REGISTRATION VALUES (107, 'Zara', 'Ali', 18)" );
        // sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";
        // sql = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";
//        updataDb("UPDATE Registration \" + \"SET first = 'olik25' WHERE id in (105)");

//        deleteDataUS("DELETE FROM REGISTRATION WHERE ID = 107");

        readTableDB("SELECT id, first, last, age FROM Registration");
        closeDataBase ();


    }

    public static Connection getDbConn( ) {
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

    public static Statement getStmt() throws SQLException {
        Statement stmt = null;
        stmt = getDbConn ( ).createStatement ( );
        return stmt;
    }

    public static void createTableDb(String sql) throws SQLException {
            getStmt().executeUpdate ( sql );
            System.out.println("Created table in given database...");
    }

    public static void addDataBase(String sql) throws SQLException {
        getStmt().executeUpdate ( sql );
        System.out.println ( "Inserted records into the table..." );
        getDbConn ( ).commit ( );
        getDbConn ( ).rollback ( );

    }

    public static void updataDb(String sql) throws SQLException {
            getStmt().executeUpdate(sql);
    }

    public static void closeDataBase( ) {
        try {
            if (getDbConn ( ) != null) getDbConn ( ).close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
        try {
            if (getStmt() != null) getStmt().close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
    }

    public static void readTableDB(String sql) throws SQLException, ClassNotFoundException {
        // STEP 4: Extract data from result set
            while(getResultSet(sql).next()) {
                // Retrieve by column name
                int id  = getResultSet(sql).getInt("id");
                int age = getResultSet(sql).getInt("age");
                String first = getResultSet(sql).getString("first");
                String last = getResultSet(sql).getString("last");
                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
    }

    public static ResultSet getResultSet(String sql) throws SQLException {
            ResultSet rs = null;
            rs = getStmt().executeQuery (sql);
            return rs;
    }

    public static void deleteDataUS(String sql) throws SQLException {
            getStmt().executeUpdate(sql);
    }

}




