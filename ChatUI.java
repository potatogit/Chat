import java.util.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUI extends JFrame {
	public static void main(String argc[]){
		ChatUI c=new ChatUI();
		//ChatConnection cc=new ChatConnection("192.168.0.102");
		//cc.sendMessage("hello");
		//Receive r=new Receive(cc);
	}
	
	public static InetAddress host;
	private static JPanel contentPane;
	private static String name;
	public static JTextArea textArea;
	ChatConnection chatConnection=null;
	Receive receive=null;
	private boolean isFree=true;
	public ChatUI(){
		setMenu();
	}	
	private void setMenu(){
		//SwingUtilities.updateComponentTreeUI(this);
		try {
			host=InetAddress.getLocalHost();
		}catch(Exception e){}
		name="Chat       host IP: "+host.getHostAddress();
		setTitle(name);
		setResizable(false);//�ı��С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رհ�ť���趨
		setBounds(200,100,450,450);//x��y��ľ��룬����
		contentPane = new JPanel() ;
		//{
//			//private static final long serialVersionUID = 1L;
//
//		};
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//IP address field
		final JTextField textField=new JTextField();
		textField.setBounds(20,10,200,30);
		getContentPane().add(textField);

		// ������Ϣ��ʾ����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 50, 400, 250);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);//�����Զ����й��� 
		textArea.setWrapStyleWord(true);//������в����ֹ��� 
		textArea.setFont(new Font("sdf", Font.BOLD, 13));
		scrollPane.setViewportView(textArea);

		// ��������
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 320, 400, 70);
		getContentPane().add(scrollPane_1);

		final JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);//�����Զ����й��� 
		textArea_1.setWrapStyleWord(true);//������в����ֹ��� 
		scrollPane_1.setViewportView(textArea_1);

		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(350,390,50,36);	
		//getRootPane().setDefaultButton(btnNewButton);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("Connect");
		btnNewButton1.setBounds(235,10,80,30);
		getContentPane().add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("Disconnect");
		btnNewButton2.setBounds(320,10,100,30);
		getContentPane().add(btnNewButton2);
		
		this.setVisible(true);
		// send��ť
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String info = textArea_1.getText();
				// �Լ���������ҲҪ��ʵ���Լ�����Ļ����
				textArea.append(" ��˵:\r\n" + info + "\r\n");
				try{
					chatConnection.sendMessage(info);
				}catch(Exception eee){
					JOptionPane.showMessageDialog(getContentPane(), "Sending message Failed");
				}
				textArea_1.setText(null);
				textArea_1.requestFocus();
			}
		});
		
		// connect��ť
		btnNewButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(isFree&&textField.getText()!=null){
					String ipAddr=textField.getText();
					try{
						 chatConnection=new ChatConnection(ipAddr);
						// chatConnection.displayReceivedMessage();
						 receive=new Receive(chatConnection);
						 //System.out.println(ipAddr);
						 isFree=false;
						 JOptionPane.showMessageDialog(getContentPane(), "Connection Succeeded");
					}catch(Exception ee){
						JOptionPane.showMessageDialog(getContentPane(), "Connection Failed");
					}
				}
			}
		});
		
		// disconnect��ť
		btnNewButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					try{
						chatConnection.chatConnectionClose();
						isFree=true;
						JOptionPane.showMessageDialog(getContentPane(), "Disconnect successfully");
					}catch(Exception ee){}
			}
		});
		
	}
}
