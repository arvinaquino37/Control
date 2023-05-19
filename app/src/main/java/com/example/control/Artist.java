package com.example.control;

public class Artist {

    String privilege;
    String email;
    String password;
    String userId;
//    String Uid;

    public Artist() {

    }

    public Artist(String email, String password, String privilege, String userId) {
        this.email = email;
        this.privilege = privilege;
        this.password = password;
        this.userId = userId;
//        this.Uid = Uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPrivilege() {
        return privilege;
    }


    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }


//    public String getUid() {
//        return Uid;
//    }

}
