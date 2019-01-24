package com.fernandopaniagua.model;

import com.fernandopaniagua.exceptions.ConnectionNotEnabledException;
import com.fernandopaniagua.exceptions.DuplicateEmailException;
import com.fernandopaniagua.exceptions.UnknownException;
import com.fernandopaniagua.persistence.DatabaseManager;

public class UsersManager {
    /*
    public boolean checkCredential(User u) throws UserNotFoundException{
        DatabaseManager dbm = new DatabaseManager();
        User storedUser = dbm.getUser(u);
        return isPasswordOk(u.getPassword(), storedUser.getPassword());
    }
    private boolean isPasswordOk(String p1, String p2){
        return p1.equals(p2);
        //return p1.equals(p2)?true:false;
    }
    */
    public void addUser(User _user) throws 
            ConnectionNotEnabledException, 
            DuplicateEmailException, 
            UnknownException {
        DatabaseManager dbm = new DatabaseManager();
        dbm.addUser(_user);
    }
    
    
}
