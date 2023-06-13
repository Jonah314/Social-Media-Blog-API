package Controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
        
    }
    
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegister);
        app.post("/login", this::postUserLoggin);
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/accounts/{account_id}/messages", this::getRAMFUHandler);
        app.get("/messages/{message_id}", this:: getMBIDHandler);
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

    public void postUserLoggin(Context ctx) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(),Account.class);
        Account sucessfullLoggin = accountService.userLoggin(account);
        Boolean userTest = accountService.usernameTest(account);
      
        if(userTest == false){
            ctx.status(401);
            System.out.println("usernameTest Failed at json level");
            return;
        }
        if(sucessfullLoggin == null){
            ctx.status(400);
            return;
        }else {
            ctx.json(mapper.writeValueAsString(sucessfullLoggin));
            ctx.status(200);
            System.out.println("user loggin in went through at json level");
        }
    }




    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    
     private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
        context.status(200);
    }
    
    public void postMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(),Message.class);
        System.out.println(ctx.body());
        Message addedMessage = messageService.createMessage(message);
        System.out.println("c");
        if( addedMessage == null){
            ctx.status(400);
            System.out.println("postMessagesHandler Failed");
            return;
        }else {
            System.out.println("before2");
            ctx.json(mapper.writeValueAsString(addedMessage));
            System.out.println("after2");
            ctx.status(200);
            System.out.println(" PostMessageHandler went through");
        }
    }
    public void getRAMFUHandler(Context ctx){
       System.out.println("handler started");
        ctx.json(messageService.retrieveAllMessageForUser(ctx.pathParam("account_id")));
       System.out.println("messages sent");
        ctx.status(200); 
    }

    public void getMBIDHandler(Context ctx){
        boolean bool = messageService.RMBIDTest(ctx.pathParam("message_id"));
        if( bool == true){
        ctx.json(messageService.retrieveMessageById(ctx.pathParam("message_id")));
        ctx.status(200);}
        else {
            ctx.status(200);
            System.out.println("no messages for this message_id");
            return;
        }
    }
}