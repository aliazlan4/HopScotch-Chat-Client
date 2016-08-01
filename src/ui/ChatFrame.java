package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import socket.History;
import socket.Message;
import socket.SocketClient;

public class ChatFrame extends JPanel
{
	private static int width = 890, height = 600;
	public UserInfoPanel userInfoPanelDesign;
	public ChatAreaPanel chatAreaPanelDesign;
	public TypeChatPanel typeChatPanelDesign;
	public FriendListPanel friendListPanelDesign;
	public JPanel chatHolder = new JPanel(),
			titleShower = new JPanel();
	BufferedImage background;
	
	public static JFrame mf;
	public static SocketClient client;
	public int port = 203;
	public String serverAddr = "127.0.0.1";
	public static String username;
	public String password;
	public static Thread clientThread;
	public File file;
	public String historyFile = "History.xml";
	public HistoryFrame historyFrame;
	public History hist;
	public LoginScreen loginscreen;
    public javax.swing.JList jList1;
	
	public ChatFrame()
	{
		serverAddr = JOptionPane.showInputDialog(this, "What is Server IP Address? (Default: 127.0.0.1)", "127.0.0.1");
		
		background = new BufferedImage(1200, 622, BufferedImage.TYPE_INT_RGB);
		userInfoPanelDesign = new UserInfoPanel(this);
		chatAreaPanelDesign = new ChatAreaPanel(this);
		typeChatPanelDesign = new TypeChatPanel(this, chatAreaPanelDesign);
		friendListPanelDesign = new FriendListPanel(this);
		
		initializeComponents();
		
		hist = new History(historyFile);
		
		ConnectServer();
        Thread SST = new Thread(new SplashScreen(this));
        SST.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			background = ImageIO.read(new File ("Accessories//Backgrounds//ClientBackgroundTitledWide.png"));
			g.drawImage(background, 0, 0, 1200, 622, null);
			Thread.sleep(10);
		} catch(Exception e){}
		repaint(100);
	}
	
	private void initializeComponents()
	{	
		this.setLayout(new BorderLayout());
		
		titleShower.setPreferredSize(new Dimension(0,100));
		titleShower.setOpaque(false);
		
		chatHolder.setLayout(new BorderLayout());
		chatHolder.setOpaque(false);
		
		chatHolder.add(typeChatPanelDesign, "South");
		chatHolder.add(chatAreaPanelDesign, "Center");
		
		this.add(titleShower, "North");
		this.add(friendListPanelDesign, "East");
		this.add(userInfoPanelDesign, "West");
		this.add(chatHolder, "Center");
	}
	
	public void ConnectServer(){
        try{
            client = new SocketClient(this);
            clientThread = new Thread(client);
            clientThread.start();
            client.send(new Message("test", "testUser", "testContent", "SERVER"));
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error! Server not found. Exiting", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
	
	public void showLoginScreen(){
    	loginscreen = new LoginScreen(this);
    }
	
	public static void main(String[] args)
	{
		mf = new JFrame("Hopscotch 1.0");
		
		mf.add(new ChatFrame());
		mf.setResizable(false);
		mf.setUndecorated(true);
		mf.setSize(width, height);
		mf.setLocationRelativeTo(null);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mf.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {}
            public void windowClosing(WindowEvent e) { try{ client.send(new Message("message", username, ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            public void windowClosed(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
        });
	}
}