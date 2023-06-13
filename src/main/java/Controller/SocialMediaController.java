package Controller;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


import Model.Account;
//import Model.Message;
import Service.AccountService;
//import Service.MessageService;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    //MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService();
        //this.messageService=messageService;
    }
    
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegister);

        //app.get("/messages", this::getAllMessagesHandler);
        //app.post("/messages", this::postMessagesHandler);
        
        return app;
    }

    public void postRegister(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);

        if( addedAccount == null){
            ctx.status(400);
            
        }else {
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.status(200);
            System.out.println(" postRegister wnet through");
        }
    }






    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    /*private void getAllMessagesHandler(Context context) {
        List<Message> message = new ArrayList<Message>();
        context.json(message);
        context.status(200);
    }
    */
   /*  private void postMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(),Message.class);
        Message addedMessage = messageService.createMessage(message);
        if( addedMessage != null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else {
            ctx.status(400);
        }
    }
    */

}