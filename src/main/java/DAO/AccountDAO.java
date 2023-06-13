package DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    


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
 //userloggin
public Account userLoggin(Account account){
    System.out.println("Account Dao userLoggin method started");
    Connection connection = ConnectionUtil.getConnection();
    
    try{
        // sql logic
        
        String sql = "SELECT * FROM account WHERE username = ? AND password = ? ; ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getUsername());
        ps.setString(2,account.getPassword());
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Account account2 = new Account
            (rs.getInt("account_id"),
             rs.getString("username"),
             rs.getString("password"));
        
             return account2;
        }
        


    }catch(SQLException e){
        System.out.println(e.getMessage());

        System.out.println("UserLoggin sql caught exception");
    }
    System.out.println("print statement for return null in Account DAO");
    return null;
}

    // username test to verify username is in database
    public Boolean usernameTest(Account account){
        System.out.println("Account Dao usernameTest method started");
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            // sql logic
            
            String sql = "SELECT * FROM account WHERE username = ?; ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            
            
            ResultSet rs = ps.executeQuery();
            if( rs.next()){
                System.out.println("usernameTest returned true");
                return true;
            }
            
    
    
        }catch(SQLException e){
            System.out.println(e.getMessage());
    
            System.out.println("UsernameTest sql caught exception");
        }
        System.out.println("usernameTest returned false");
        return false;
}

public Account passwordTest(Account account){
    System.out.println("Account Dao passwordTest method started");
    Connection connection = ConnectionUtil.getConnection();
    
    try{
        // sql logic
        
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?; ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Account account2 = new Account
            (rs.getInt("account_id"),
             rs.getString("username"),
             rs.getString("password"));
        
             return account2;
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());

        System.out.println("passwordTest sql caught exception");
    }
    System.out.println("Test returned false");
    return null;
}

}
