package main.java.sql;

import java.sql.*;

public class DataBaseLogger {
    private static Statement stmt;
    private static Connection dbConn;
    private static String DB_URL ="jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange";



    ///////// for example

/*    public static void main(String[] args) throws SQLException {
        DataBaseLogger dataBaseLogger = new DataBaseLogger();
        dataBaseLogger.getStmt ();


        dataBaseLogger.createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOG (" +
                " id INTEGER not NULL," +
                " userName VARCHAR(255)," +
                " userMessage VARCHAR(255)," +
                " date TIMESTAMP, " +
                " PRIMARY KEY ( id ))");

//        addDataBase ( "Tablelog", 103, "Fara", "Adfffffli");


//        updataDb("Tablelog","userName","lik",103);

//        deleteDataUS("Tablelog", 103);

        //Problem is somewhere below
        dataBaseLogger.readTableDB( "id", "userName", "userMessage", "Tablelog");
        dataBaseLogger.closeDataBase ();
    }*/

    public static void setDbConn(String DB_URL ) {
        final String JDBC_DRIVER = "org.h2.Driver";
//        DB_URL = "jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange";
        //db/stockExchange.mv.db
        //D:\github\java\ClientServer\db
        try {
            Class.forName ( JDBC_DRIVER );
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace ( );
        }
        try {
            dbConn = DriverManager.getConnection ( DB_URL );
        } catch (SQLException e1) {
            e1.printStackTrace ( );
        }

    }

    public static Connection getDbConn() {
        return dbConn;
    }

    public Statement getStmt() throws SQLException {
        return stmt;
    }

    public void setStmt(String DB_URL) throws SQLException {
        setDbConn (DB_URL);
        stmt = getDbConn ().createStatement ( );
    }

    public void createTableDb(String sql) throws SQLException {
            getStmt().executeUpdate ( sql );
            System.out.println("Created table in given database...");
    }

    public static void addDataBase(String nameTable, Integer idSet, String userNameSet, String userMessageSet) throws SQLException {
        String sql = "INSERT INTO " + nameTable + " VALUES " + "(" + idSet + ", " + "'"+ userNameSet + "'" + ", " + "'" + userMessageSet + "'" + ", " + "'" + dateTime() + "'" + ")";
        stmt.executeUpdate ( sql );
        System.out.println ( "Inserted records into the table..." );
        getDbConn ().commit ( );
        getDbConn ().rollback ( );
    }

    private static void updataDb(String nameTable, String userNameSet, String updataString, Integer idSet) throws SQLException {
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

    public void closeDataBase() {
        try {
            if (getDbConn () != null) getDbConn ( ).close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
        try {
            if (getStmt() != null) getStmt().close ( );
        } catch (SQLException se) {
            se.printStackTrace ( );
        }
    }

    public void readTableDB(String idSet, String userNameSet, String userMessageSet, String nameTable) throws SQLException {
        String sql = "SELECT * FROM " + nameTable;
        ResultSet rs = getResultSet(sql);
        System.out.println (" -----------------------------------" );
        while(rs.next()) {
                // Retrieve by column name
                int id=rs.getInt("id");
                String userName=rs.getString("userName");
                String userMessage=rs.getString("userMessage");
                String date=rs.getString("date");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(" ");
                System.out.print(", userName: " + userName);
                System.out.print(", userMessage: " + userMessage);
                System.out.print(", date: " + date);
                System.out.println ( );
                System.out.println (" -----------------------------------" );
            }
    }

    public ResultSet getResultSet(String sql) throws SQLException {
            ResultSet rs = null;
            rs = stmt.executeQuery (sql);
            return rs;
    }

    public static void deleteDataUS(String nameTable, Integer idSet) throws SQLException {
        String sql ="DELETE FROM " + nameTable + " WHERE ID = " + idSet ;
            stmt.executeUpdate(sql);
    }

}




