import java.net.*;
import java.io.*;
import java.util.*;
class ServerSend extends Thread{
	Socket s=null;
	public ServerSend(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			while(true){
				DataOutputStream dos= new DataOutputStream(s.getOutputStream());
				Scanner sl=new Scanner(System.in);
				//System.out.print("Server Says : ");
				String s1=sl.nextLine();
				dos.writeUTF(s1);
			}
		}catch(IOException e){
			System.out.println("Internal server Error");
		}
	}
}
class ServerRecieve extends Thread{
	Socket s=null;
	public ServerRecieve(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			while(true){
			DataInputStream dis=new DataInputStream(s.getInputStream());
	   		String str=dis.readUTF();
	   		System.out.println("Client say:"+str);
			}
		}catch(IOException e){
			System.out.println("Client didn't resond");
		}
	}
}
class Server{
	public static void main(String[] args) {
		try{
			ServerSocket ss=new ServerSocket(3000);
			System.out.println("Server has been started");
			Socket s = ss.accept();
			System.out.println("Client request has been accepted.");
			ServerSend send=new ServerSend(s);
			ServerRecieve recieve=new ServerRecieve(s);
			send.start();
			recieve.start();
			//s.close();
		}catch(Exception e){
		}
	}
}