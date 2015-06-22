import java.net.*;
    import java.util.*;
    /**
     * �򵥵�UDP�ͻ��ˣ�ʵ����������˷���ϵͳʱ�书��
     * �ó�����3�����ݵ���������
     */
    public class ChatUI {
             public static void main(String[] args) {
             DatagramSocket ds = null; //���Ӷ���
             DatagramPacket sendDp; //�������ݰ�����
             DatagramPacket receiveDp; //�������ݰ�����
             String serverHost = "127.0.0.1"; //������IP
             int serverPort = 10012; //�������˿ں�
             try{
                    //��������
                    ds = new DatagramSocket();
                    //��ʼ
                    InetAddress address = InetAddress.getByName(serverHost);
                    byte[] b = new byte[1024];
                    receiveDp = new DatagramPacket(b,b.length);
                    System.out.println("�ͻ���׼�����");
                    //ѭ��30�Σ�ÿ�μ��0.01��
                    for(int i = 0;i < 30;i++){
                    	//��ʼ����������
                        Date d = new Date(); //��ǰʱ��
                        String content = d.toString(); //ת��Ϊ�ַ���
                        byte[] data = content.getBytes();
                        //��ʼ�����Ͱ�����
                        sendDp = new DatagramPacket(data,data.length,address, serverPort);
                        //����
                        ds.send(sendDp);
                        //�ӳ�
                        Thread.sleep(10);
                        //����
                        ds.receive(receiveDp);
                        //��ȡ�������ݣ������
                        byte[] response = receiveDp.getData();
                        int len = receiveDp.getLength();
                        String s = new String(response,0,len);
                        System.out.println("�������˷���Ϊ��" + s);
                   }
       }catch(Exception e){e.printStackTrace();}
      finally{
    	  try{
    		  //�ر�����
    		  ds.close();}
    	  catch(Exception e){}
    	  }
      }
 }
