package com.example.simpleapp;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHelper {

    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/db","root","1234");
              Log.d("Access Database","Success");
        } catch (Exception ex) {
            Log.e("SQLError: ", ex.getMessage());
        }
        return con;
    }

    public boolean addUser(String id, String username, String password, String email) {
        Connection conn = new ConnectionHelper().CONN();
        String query = "INSERT INTO users values(" + id + "," + "'" + username + "','" + password + "','" + email +"')";
        Log.d("QUERY: ", query);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUser(String username, String password){
        Connection conn = new ConnectionHelper().CONN();
        String query = "SELECT username,password from users WHERE username=" + "'" + username + "' and " + "password=" + "'" + password + "'";
        Log.d("QUERY: ", query);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()) {
                while (result.next()){
                    String re = result.getString(1) + "/" + result.getString(2);
                    Log.d("RESULT: ", re);
                }
                statement.close();
                conn.close();
                return true;
            } else {
                statement.close();
                conn.close();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
