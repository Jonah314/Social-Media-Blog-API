package DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
//userlogin

//userRegistration
public Account createAccount(Account account){
    System.out.println("Account Dao createAccount method started");
    Connection connection = ConnectionUtil.getConnection();
    
    try{
        // sql logic
        
        String sql = "INSERT INTO account(username, password) VALUES(?,?); ";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setString(1,account.getUsername());
        ps.setString(2,account.getPassword());
        
        ps.executeUpdate();
        ResultSet pkeyResultSet= ps.getGeneratedKeys();
        
        if(pkeyResultSet.next()){
            int generated_account_id = (int) pkeyResultSet.getInt(1);
            String username = account.getUsername();
            String password = account.getPassword();
            System.out.println("Account DAO return new Account, sucessfull");
            return new Account(generated_account_id, username,password);
        }


    }catch(SQLException e){
        System.out.println(e.getMessage());

        System.out.println("Account DAO caught exception");
    }
    System.out.println("print statement for return null in Account DAO");
    return null;
}

}
