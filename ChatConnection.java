import java.util.*;
import java.net.*;
public class ChatConnection{
	public DatagramSocket dsc=null;
	public int port =10011;
	public DatagramPacket sendDp=null;
	public DatagramPacket receiveDp=null;
	InetAddress addr=null;

	public ChatConnection(String ipAddr){
		try{
			dsc=new DatagramSocket(port);
			addr=InetAddress.getByName(ipAddr);
			//System.out.println(addr);
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
	public void chatConnectionClose(){
		try{
			dsc.close();
		}catch(Exception e1){}
	}
	public void sendMessage(String str){
		byte b[]=str.getBytes();
		//System.out.println(addr+"  "+Arrays.toString(b));
		try{
			sendDp=new DatagramPacket(b,b.length,addr,port);
			dsc.send(sendDp);
			//System.out.println(Arrays.toString(b));
		}catch(Exception e){
			System.out.println("Send Error");
		}
	}

}
