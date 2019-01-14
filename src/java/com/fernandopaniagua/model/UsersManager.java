package com.fernandopaniagua.model;

import com.fernandopaniagua.exceptions.UserNotFoundException;
import com.fernandopaniagua.persistence.DatabaseManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersManager {
    public boolean checkCredential(User u) throws UserNotFoundException{
        DatabaseManager dbm = new DatabaseManager();
        User storedUser = dbm.getUser(u);
        return isPasswordOk(u.getPassword(), storedUser.getPassword());
        
    }
    private boolean isPasswordOk(String p1, String p2){
        return p1.equals(p2);
        //return p1.equals(p2)?true:false;
    }
}
