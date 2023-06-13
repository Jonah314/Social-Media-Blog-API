package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;


public class MessageService {
public MessageDAO messageDAO;
public MessageService(){
        messageDAO = new MessageDAO();
    }
public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
 
public List<Message> getAllMessages(){
        List<Message> message = new ArrayList<Message>();
        message = messageDAO.retrieveAllMessages();
        return message;
    }

    
public Message createMessage(Message message){
    System.out.println("createMessage Service started");
    //checking if message is empty
    if(message.getMessage_text().isEmpty()== true){
        return null;
    }
    //checking if string is smaller than 254 characters
    if(message.getMessage_text().length()>254){
        return null;
    }
    return messageDAO.createMessage(message);
}

public List<Message> retrieveAllMessageForUser(String string){
   System.out.println("Message service started");
    int i = Integer.parseInt(string);
    
    return messageDAO.retrieveAllMessagesForUser(i);
}

public Message retrieveMessageById(String string){
    int i = Integer.parseInt(string);
    if(RMBIDTest(i) ==true){
    return messageDAO.retrieveMessagesByMessageId(i);}
    return null;
}
public Boolean RMBIDTest(int i){
    if( messageDAO.MessageITest(i)==true){
        return true;
    }
    return false;
}
public Boolean RMBIDTest(String string){
    int i = Integer.parseInt(string);
    if( messageDAO.MessageITest(i)==true){
        return true;
    }
    return false;
}

}
