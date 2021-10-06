package com.example.mysqlservlet;

import java.sql.*;

public class mySQL {

    private Connection conn;
    private Statement statement;

    mySQL(String connectionString, String username, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString,username,password);
            statement = conn.createStatement();
            System.out.println("Connection Succes");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void insertContact(String name, String tel_number){

        String insertQuery= "INSERT INTO contacts (name, tel_number) VALUES"+
                "('"+name+"', '"+ tel_number+"')";
        try {
            statement.executeUpdate(insertQuery);
            System.out.println("Contact Inserted.");
        } catch (SQLException throwables) {
            System.out.println("Insert Error!");
            throwables.printStackTrace();
        }
    }

    public String getContact(String name){
        String tel_number=null;
        String query= "SELECT tel_number FROM contacts WHERE name = ? LIMIT 1";
        try {
            PreparedStatement preStatement=conn.prepareStatement(query);
            preStatement.setString(1, name);
            ResultSet rs = preStatement.executeQuery();

            while (rs.next()){
                tel_number=rs.getString("tel_number");
            }

        } catch (SQLException throwables) {
            System.out.println("Read Error!");
            throwables.printStackTrace();
        }
        return tel_number;
    }

    public void updateContact(String name, String tel_number){
        String query = "UPDATE contacts SET tel_number = ? WHERE name= ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, tel_number);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void connectionClose(){
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException throwables) {
            System.out.println("Still connected!");
            throwables.printStackTrace();
        }
    }
}
