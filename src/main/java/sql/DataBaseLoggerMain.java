package main.java.sql;

import java.sql.SQLException;

public class DataBaseLoggerMain extends DataBaseLogger {
    public static void main(String[] args) throws SQLException {

        DataBaseLogger dataBaseLogger = new DataBaseLogger();
        String DB_URL ="jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange";
        dataBaseLogger.setStmt(DB_URL);
        dataBaseLogger.setDbConn (DB_URL);

//        addDataBase ( "Tablelog", 103, "Fara", "Adfffffli");


//        updataDb("Tablelog","userName","lik",103);

//        deleteDataUS("Tablelog", 103);

        dataBaseLogger.readTableDB( "id", "userName", "userMessage", "Tablelog");
        dataBaseLogger.closeDataBase ();
    }
    }
