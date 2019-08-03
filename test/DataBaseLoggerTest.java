import main.java.sql.DataBaseLogger;
import org.junit.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataBaseLoggerTest {
    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() throws SQLException {
        //GIVEN
        DataBaseLogger dataBaseLogger = new DataBaseLogger();
        try {
            dataBaseLogger.getStmt ();
        } catch (SQLException e) {
            System.out.println("Oooops, something wrong with statment: ");
            e.printStackTrace();
        }
        dataBaseLogger.createTableDb("CREATE TABLE IF NOT EXISTS  TABLELOG (" +
                " id INTEGER not NULL," +
                " userName VARCHAR(255)," +
                " userMessage VARCHAR(255)," +
                " date TIMESTAMP, " +
                " PRIMARY KEY ( id ))");

        //WHEN
        //https://stackoverflow.com/a/2943166
        DatabaseMetaData dbm = dataBaseLogger.getDbConn().getMetaData();
        // check if "employee" table is there
        ResultSet tables1 = dbm.getTables(null, null, "TABLELOG", null);
        ResultSet tables2 = dbm.getTables(null, null, "TABLELOG2", null);

        //THEN
        /*
        Почему и true и false? Потому что если бы я проверял только на тру то есть вероятность что там баг и он всегда возвращает истину.
        А так я проверил что если мы ищем заведомо неправильное имя таблицы то его не долдно существовать
        */
        assertTrue(tables1.next());
        assertFalse(tables2.next());
    }
}
