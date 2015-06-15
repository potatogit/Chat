import java.net.DatagramPacket;

public class Receive extends Thread{
	ChatConnection cc=null;
	public Receive(ChatConnection cc){
		this.cc=cc;
	}
	public void run(){//
		byte b[]=new byte[100000];
		try{
			while(true){
				cc.receiveDp=new DatagramPacket(b,b.length);
			    cc.dsc.receive(cc.receiveDp);
			    byte bb[]=cc.receiveDp.getData();
			    int len=cc.receiveDp.getLength();
			    String str=new String(bb,0,len);
			    ChatUI.textArea.append(cc.addr+"说："+str);
			}
			
		}catch(Exception e){}
	}
}
