import java.net.DatagramPacket;

public class Receive extends Thread{
	ChatConnection cc=null;
	public Receive(ChatConnection cc){
		this.cc=cc;
		start(); // Start thread
	}
	public void run(){  // run thread
		byte b[]=new byte[100000];
		try{
			while(true && cc!=null){
				cc.receiveDp=new DatagramPacket(b,b.length);
			    cc.dsc.receive(cc.receiveDp);
			    byte bb[]=cc.receiveDp.getData();
			    int len=cc.receiveDp.getLength();
			    String str=new String(bb,0,len);
			    ChatUI.textArea.append(cc.addr+" says£º\r\n"+str+"\r\n");
			}
			
		}catch(Exception e){}
	}
}