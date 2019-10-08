package client;

import main.java.sql.DataBaseLogger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientWindowTest2 {



    @Test
        public  void writeDataBase( ) throws SQLException {
            ClientWindow clientWindow = new ClientWindow ();
            DataBaseLogger dataBaseLogger = new DataBaseLogger ();

            dataBaseLogger.setStmt("jdbc:h2:file:D:/github/java/ClientServer/db/stockExchange");

            String setNameUser =clientWindow.jtfName.getText ();
            String setMessageUser = clientWindow.jtfMessage.getText ();
            Integer countId = 1;

        String sql = "SELECT * FROM TABLELOG";
        ResultSet rs = dataBaseLogger.getResultSet(sql);

        int resultCheck = 0;
        while(rs.next()) {
//            System.out.println ( "resCh=" + resultCheck );
//            if (rs.getInt (1 ) == 1){
//                resultCheck = rs.getInt ( 1 );
//                resultCheck ++;
//                System.out.println ( "resCh count =" + resultCheck );

//            }
            resultCheck = rs.getInt ( 1 );
            resultCheck ++;

            System.out.println ( "resCh count =" + resultCheck );
            Assert.assertEquals(2,resultCheck);
        }

//              dataBaseLogger.addDataBase ( "Tablelog", 1, "Fara", "Adfffffli");
            dataBaseLogger.addDataBase ( "Tablelog", resultCheck, setNameUser, setMessageUser);






        }


    @Test
    public void getClientName( ) {
    }

    @Test
    public void sendMsg( ) {
    }

    @Test
    public void writeLog( ) {
    }

    @Test
    public void cointId( ) {

    }

    @Test
    public void read( ) {
    }

    @Test
    public void update( ) {
    }
}
