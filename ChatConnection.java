import java.util.*;
import java.net.*;
public class ChatConnection extends Thread{
	private DatagramSocket dsc=null;
	private int port =10010;
	private DatagramPacket sendDp=null;
	private DatagramPacket receiveDp=null;
	InetAddress addr=null;

	public ChatConnection(String ipAddr){
		try{
			dsc=new DatagramSocket(port);
			addr=InetAddress.getByName(ipAddr);
			//System.out.println("Set up Successfully;");
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	public void displayReceivedMessage(){
		while(true){
			String text=receiveMessage();
			ChatUI.textArea.append(addr+" 说:\r\n" + text + "\r\n");
		}
	}
	public void chatConnectionClose(){
		try{
			dsc.close();
		}catch(Exception e1){}
	}
	public void sendMessage(String str){
		byte b[]=str.getBytes();
		//System.out.println(Arrays.toString(b));
		try{
			sendDp=new DatagramPacket(b,b.length,addr,port);
			dsc.send(sendDp);
			System.out.println(Arrays.toString(b));
		}catch(Exception e){}
	}
	public String receiveMessage(){
		byte b[]=new byte[100000];
		try{
			receiveDp=new DatagramPacket(b,b.length);
			dsc.receive(receiveDp);
			byte bb[]=receiveDp.getData();
			int len=receiveDp.getLength();
			String str=new String(bb,0,len);
			return str;
		}catch(Exception e){}
		return null;
	}

}
