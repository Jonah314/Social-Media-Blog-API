package Service;

import java.io.IOException;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public void AcccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    // user registration
   public Account createAccount(Account account){
    if ( account.getPassword().length()<4){
        return null;
    }
    if(account.getUsername().length()<=0){
        return null;
    }
    
    System.out.println("account Service initiated");
    return accountDAO.createAccount(account);
   }
   // user loging
   public Account userLoggin(Account account){
    System.out.println("AccountService innitiaited");
    
    if(usernameTest(account)==true){
    return accountDAO.userLoggin(account);}
    return null;
   }

   // checking to see if username is in database
   public boolean usernameTest(Account  account){
    System.out.println("user Test started");
    if ( accountDAO.usernameTest(account) != true){
        return false;
    }

    return true;
   }

   

}
