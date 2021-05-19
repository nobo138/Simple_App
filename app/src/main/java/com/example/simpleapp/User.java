package com.example.simpleapp;

public class User {
    private int id = 0;
    private String Username;
    private String Email;
    private String Password;

    //Contractor to SQLiteConnect is not error
    public User(){
        id = 1;
        Username ="";
        Email = "";
        Password= "";
    }

    //Contractor to Register insert data
    public User(int i, String name, String email, String password) {
        id = i;
        Username = name;
        Email = email;
        Password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id + 1; }

    public String getName() { return Username; }

    public void setName(String username){this.Username = username;}

    public String getEmail(){return Email;}

    public void setEmail(String email){this.Password = email;}

    public String getPassword(){return Password;}

    public void setPassword(String password){this.Password = password;}


}
