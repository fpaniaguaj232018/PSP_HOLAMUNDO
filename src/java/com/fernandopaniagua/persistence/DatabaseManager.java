package com.fernandopaniagua.persistence;

import com.fernandopaniagua.exceptions.UserNotFoundException;
import com.fernandopaniagua.model.User;


public class DatabaseManager {
    private String email = new String("fernando@gmail.com");
    private String password = new String("12345");
    
    public User getUser(User _user) throws UserNotFoundException {
        /*
        1. String sql = "select * from T_USUARIOS where email=" + _user.getEmail()"...
        2. Hacemos la consulta
        3. if no existe -> lanzamos la UserNotFoundException
        4. if existe -> retorno el user con lo datos de la bbdd
        */
        if (_user.getEmail().equals(email)){
            return new User(email, password);
        } else {
            throw new UserNotFoundException();
        }
    }
    
}
