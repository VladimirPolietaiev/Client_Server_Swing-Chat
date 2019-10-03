package main.java.sql;

import client.ClientWindow;

import java.io.IOException;

public class getDataChat {
    static ClientWindow clientWindow = new ClientWindow ();

    public static String getDataUserName( ){
        String getDataUserName = clientWindow.getClientName();
        return getDataUserName;
    }

    public void getDataUserMessage() throws IOException {
        String getDataUserMessage = clientWindow.jtfMessage.getText();
    }

}
