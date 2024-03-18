import java.io.*;
import java.net.*;
import java.util.*;

/*
 * This is the main chat server class
 * 
 */


public class ChatServer {

    private int port;
    private Set<String> userName = new HashSet<>();
    private Set<UserThread> userThread = new HashSet<>();

    public ChatServer(int port){
        /*This constructor allows the user to specifiy which port to use */
        this.port=port;
    }

    public ChatServer(){
        /* This constructor uses a hardcoded port for making connections */
        this.port = 9000;
    }

    public void startServer(){
        try(ServerSocket serverSock = new ServerSocket(port)){
            System.out.println("The Chat Server is listening on port " + port);
            while(true){
                Socket socket = serverSock.accept();
                System.out.println("New user connected");
                UserThread newUser = new UserThread(socket, this);
                userThread.add(newUser);
                newUser.start();
            }
        }
        catch(IOException e){
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        ChatServer server = new ChatServer();
        server.startServer();
    }

    public void sendMessage(String message, UserThread excludedUser){
        for(UserThread specUser : userThread){
            if(specUser != excludedUser){
                specUser.sendMessage(message);
            }
        }
    }

    public void addUser(String user){
        userName.add(user);
    }
    public void removeUser(String user, UserThread userT){
        boolean removed = userName.remove(user);
        if(removed){
            userThread.remove(userT);
            System.out.println("The user " + user + " has left the chat.");
        }
    }

    public Set<String> getUserNames(){
        return this.userName;
    }

    public boolean hasUsers(){
        return !this.userName.isEmpty();
    }
    
}
