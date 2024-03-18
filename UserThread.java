import java.io.*;
import java.net.*;
import java.util.*;

/*This class uses the thread class to allow for multiple connections at a time */

public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer server){
        this.socket = socket;
        this.server = server;
    }

    public void run(){
        try{
            InputStream inp = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));

            OutputStream outp = socket.getOutputStream();
            writer = new PrintWriter(outp, true);

            printUsers();

            String userName = reader.readLine();
            server.addUser(userName);

            String serverOutMessage = "New user connected: " + userName;
            server.sendMessage(serverOutMessage, this);

            String clientMessage;

            do{
                clientMessage = reader.readLine();
                serverOutMessage = "["+userName+"]: " + clientMessage;
                server.sendMessage(serverOutMessage, this); 
            }while(!clientMessage.equals("leave"));

            server.removeUser(userName, this);
            socket.close();

            serverOutMessage = userName + " has left the server.";
            server.sendMessage(clientMessage, this);
        } catch(IOException e){
            System.out.println("Error in the UserThread: "+ e.getMessage());
            e.printStackTrace();
        }
        
    }

    public void printUsers(){
        if(server.hasUsers()){
            writer.println("Connected users: " + server.getUserNames());
        }
        else{
            writer.println("No other users are connected");
        }
    }

    public void sendMessage(String message){
        writer.println(message);
    }



}
