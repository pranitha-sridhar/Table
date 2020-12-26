package com.example.table;

import android.annotation.SuppressLint;
import android.os.StrictMode;


import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String classs="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://182.65.250.79/table_details";
    String user="prani";
    String password="prani@4";

    /*public Connection CONN(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String Connurl=null;
        try {
            Class.forName(classs);
            connection= (Connection) DriverManager.getConnection(url,user,password);
            connection= (Connection) DriverManager.getConnection(Connurl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/
}
