package com.student.conn;

import com.student.common.Comm;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectJdbc {
    public static Connection con (){
        try {
            Connection connection = DriverManager.getConnection(Comm.MY_SQL_URL,Comm.USERNAME_MYSQL,Comm.PASSWORD_MYSQL);
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
