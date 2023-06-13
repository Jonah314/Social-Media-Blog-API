package Service;

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
    // going to come back and fix registration later, add business logic etc.
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

}
