package com.example.Connections;

import com.example.Table.*;

public class UserSession {
    private static UserSession instance;
    private UserTable loggedInUser;

    private UserSession() {
        this.loggedInUser = null;  
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(UserTable user) {
        this.loggedInUser = user;
    }

    public UserTable getLoggedInUser() {
        return loggedInUser;
    }

    public void clearSession() {
        loggedInUser = null;
    }

    public int getLoggedInRole() {
        if (loggedInUser != null) {
            return loggedInUser.getRole_user();
        }
        return 0;
    }

    public int getLoggedInID() {
        if (loggedInUser != null) {
            return loggedInUser.getId_user();
        }
        return 0;
    }
}
