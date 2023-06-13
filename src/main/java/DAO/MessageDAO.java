package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    
    public Message createMessage(Message message){
        System.out.println("createMessage DAO started");
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?,?,?); ";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1,message.getPosted_by());
        ps.setString(2, message.getMessage_text());
        ps.setLong(3,message.getTime_posted_epoch());
        ps.executeUpdate();
        ResultSet pkeyResultSet= ps.getGeneratedKeys();
        
        if(pkeyResultSet.next()){
            int generated_message_id = (int) pkeyResultSet.getInt(1);

            return new Message(generated_message_id, message.getPosted_by(),message.getMessage_text(), message.getTime_posted_epoch());
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
        System.out.println(" create message failed in DAO");
    }
    return null;
}
/* 
public Message deleteMessage(Message message){
    Connection connection = ConnectionUtil.getConnection();
    try{
        // sql logic
        
        String sql = "DELETE FROM message WHERE message.message_id = ?; ";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1,message.getMessage_id());
        ps.executeUpdate();

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}

public List<Message> retrieveAllMessages(){
    Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try{
        String sql = "SELECT * FROM message; ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messages.add(message);
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;

}
*/

    public List<Message> retrieveAllMessagesForUser(int account_id){
    System.out.println("Message DAO started");
        Connection connection = ConnectionUtil.getConnection();
    List<Message> messages = new ArrayList<>();
    try{
        String sql = "SELECT * FROM message WHERE posted_by = ?; ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,account_id);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Message message2 = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messages.add(message2);
                
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return messages;

}

/* 
public Message retrieveMessagesByMessageId(int message_id){
    Connection connection = ConnectionUtil.getConnection();
    
    try{
        String sql = "SELECT * FROM message WHERE message_id = ?; ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,message_id);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Message message = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
            return message;
        }

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;

}

public Message updateMessage(Message message){
    Connection connection = ConnectionUtil.getConnection();
    
    try{
        String sql = "UPDATE message SET message_text = '?', time_posted_epoch = '?' WHERE message_id = ?; ";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1,message.getMessage_text());
        ps.setLong(2, message.getTime_posted_epoch());
        ps.setInt(3,message.getMessage_id());
        ResultSet rs = ps.executeQuery();
        
    
            Message messageUpdate = new Message(
                rs.getInt("message_id"), 
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
            return messageUpdate;
        

    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;

}
*/
}
