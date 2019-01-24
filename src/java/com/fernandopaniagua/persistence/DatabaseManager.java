package com.fernandopaniagua.persistence;

import com.fernandopaniagua.exceptions.ConnectionNotEnabledException;
import com.fernandopaniagua.exceptions.DuplicateEmailException;
import com.fernandopaniagua.exceptions.UnknownException;
import com.fernandopaniagua.exceptions.UserNotFoundException;
import com.fernandopaniagua.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    protected Connection conexion;
    protected static final String DRIVER = "com.mysql.jdbc.Driver";
    protected static final String SERVIDOR = "jdbc:mysql://localhost:3306/game_database";
    protected static final String USER = "benzema";
    protected static final String PASSWORD = "benzema";

    public DatabaseManager() throws ConnectionNotEnabledException  {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new ConnectionNotEnabledException();
        }
    }

    private void cerrarConexion() throws SQLException {
        conexion.close();
    }

    private void establecerConexion() throws SQLException {
        conexion = DriverManager.getConnection(SERVIDOR, USER, PASSWORD);
    }
    
    
    /*public User getUser(User _user) throws UserNotFoundException {
        /*
        1. String sql = "select * from T_USUARIOS where email=" + _user.getEmail()"...
        2. Hacemos la consulta
        3. if no existe -> lanzamos la UserNotFoundException
        4. if existe -> retorno el user con lo datos de la bbdd
     */
 /*
        if (_user.getEmail().equals(email)){
            return new User(email, password);
        } else {
            throw new UserNotFoundException();
        }
    }*/
    public void addUser(User _user) throws 
            DuplicateEmailException,
            UnknownException {
        try {
            if (conexion==null || conexion.isClosed()){
                establecerConexion();
            }
            String sql = "INSERT INTO users(email, password, name, surname, ip)"+
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, _user.getEmail());
            ps.setString(2, _user.getPassword());
            ps.setString(3, _user.getName());
            ps.setString(4, _user.getSurname());
            ps.setString(5, _user.getIp());
            ps.executeUpdate();
        } catch (SQLException ex) {
            //1062 ERROR CODE DE PRIMARY KEY DUPLICADA
            if (ex.getErrorCode()==1062){
                throw new DuplicateEmailException();
            } else {
                throw new UnknownException(ex.getMessage() + ":" + ex.getErrorCode());
            }
        }
    }
}
