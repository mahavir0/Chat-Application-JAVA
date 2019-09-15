import java.net.*;
import java.io.*;
import java.util.*;
class ClientSend extends Thread{
    Socket socket=null;
    public ClientSend(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try{
            while(true){
                DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                //System.out.print("Client Says : ");
                Scanner sl=new Scanner(System.in);
                String mes=sl.nextLine();
                dos.writeUTF(mes);
            }
        }catch(IOException e){
            System.out.println("Connection Error");
        }
    }
}
class ClientRecieve extends Thread{
    Socket socket=null;
    public ClientRecieve(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try{
            while(true){
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            String s=dis.readUTF();
            System.out.println("Server Says : " + s);
            }
        }catch(IOException e){
            System.out.println("Server didn't resond");
        }
    }
}
public class Client{
	public static void main(String[] args) {
		try{
			Socket socket=new Socket("localhost",3000);
  	 		System.out.println("client has been started");
    		//System.out.println("waiting for server");
            ClientRecieve receive=new ClientRecieve(socket);
            ClientSend send=new ClientSend(socket);
            receive.start();
            send.start();
			//socket.close();
    	}catch(Exception e){

    	}	
	}
}