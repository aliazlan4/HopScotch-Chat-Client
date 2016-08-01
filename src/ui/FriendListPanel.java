package ui;

import socket.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import socket.Message;

public class FriendListPanel extends JPanel implements MouseListener
{
	private ImageIcon expand = new ImageIcon("Accessories//Buttons//Expand.png"),
			contract = new ImageIcon("Accessories//Buttons//Contract.png"),
			signoutIcon = new ImageIcon("Accessories//Buttons//signout.png"),
			sendFileIcon = new ImageIcon("Accessories//Buttons//send - Copy.png"),
			historyIcon = new ImageIcon("Accessories//Buttons//history.png");
	private JButton resizer = new JButton(expand),
			signout = new JButton(signoutIcon),
			sendFile = new JButton(sendFileIcon),
			history = new JButton(historyIcon);
	private JPanel friendList = new JPanel(),
			Buttons = new JPanel();
	private JScrollPane scroll;
	public ChatFrame ui;
	public HistoryFrame historyFrame;
    public History hist;
	
	public JList jList1;
	public DefaultListModel model;
	
	public FriendListPanel(ChatFrame ui)
	{
		this.ui = ui;
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		this.setBackground(new Color(0,255,0,30));
		this.setPreferredSize(new Dimension(30,0));
		
		resizer.setBorderPainted(false);
		resizer.setFocusPainted(false);
		resizer.setContentAreaFilled(false);
		resizer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		sendFile.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(0,163,94)));
		sendFile.setFocusPainted(false);
		sendFile.setContentAreaFilled(false);
		sendFile.setPreferredSize(new Dimension(0,58));
		sendFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		signout.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, new Color(0,163,94)));
		signout.setFocusPainted(false);
		signout.setContentAreaFilled(false);
		signout.setPreferredSize(new Dimension(0,58));
		signout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		history.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(0,163,94)));
		history.setFocusPainted(false);
		history.setContentAreaFilled(false);
		history.setPreferredSize(new Dimension(0,58));
		history.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		jList1 = new JList();
		jList1.setModel((model = new DefaultListModel()));
		model.addElement("All");
		jList1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		jList1.setOpaque(false);
		jList1.setBackground(new Color(0,0,0,0));
		jList1.setSelectedIndex(0);
		
		friendList.add(jList1);
		
		friendList.setOpaque(false);
		
		changeLookAndFeel("Nimbus");
		scroll = new JScrollPane(friendList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);
		scroll.setViewportBorder(null);
		changeLookAndFeel("Metal");
		
		Buttons.setLayout(new BorderLayout());
		Buttons.add(history, "North");
		Buttons.add(sendFile,"Center");
		Buttons.add(signout,"South");
		
		resizer.addMouseListener(this);
		signout.addMouseListener(this);
		sendFile.addMouseListener(this);
		history.addMouseListener(this);
		
		this.add(resizer);
	}
	private void frameExpanded()
	{
		this.removeAll();
		
		resizer.setIcon(contract);
		resizer.setBorderPainted(true);
		resizer.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0,163,94)));
		scroll.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0,163,94)));
		this.setBackground(new Color(0,0,0,150));
		this.setPreferredSize(new Dimension(310,0));
		this.setLayout(new BorderLayout());
		this.add(resizer, "North");
		this.add(scroll, "Center");
		this.add(Buttons, "South");
		
		((JFrame) SwingUtilities.getWindowAncestor(this)).setSize(1200, 600);
		((JFrame) SwingUtilities.getWindowAncestor(this)).setLocationRelativeTo(null);
	}
	private void frameContracted()
	{
		this.removeAll();

		resizer.setIcon(expand);
		resizer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.green));
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.green));
		this.setBackground(new Color(0,255,0,30));
		this.setPreferredSize(new Dimension(30,0));
		this.setLayout(new FlowLayout());
		this.add(resizer);
		
		((JFrame) SwingUtilities.getWindowAncestor(this)).setSize(890, 600);
		((JFrame) SwingUtilities.getWindowAncestor(this)).setLocationRelativeTo(null);
	}
	
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource() == resizer && resizer.getIcon().equals(expand))
		{
			frameExpanded();	
		}
		else if(me.getSource() == resizer && resizer.getIcon().equals(contract))
		{
			frameContracted();	
		}
		else if(me.getSource() == signout)
		{
			ui.client.send(new Message("message", ui.username, ".bye", "SERVER"));
			ui.clientThread.stop();
			restart();
		}
		else if(me.getSource() == sendFile)
		{
			changeLookAndFeel("Windows");
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.showDialog(this, "Select File");
	        ui.file = fileChooser.getSelectedFile();
	        if(ui.file.isFile()){
	        	long size = ui.file.length();
	            if(size < 120 * 1024 * 1024){
	                ui.client.send(new Message("upload_req", ui.username, ui.file.getName(), jList1.getSelectedValue().toString()));
	            }
	            else{
	                ui.chatAreaPanelDesign.chatArea.append("[Application > Me] : File is size too large\n");
	            }
	        }
	        changeLookAndFeel("Metal");
		}
		else if(me.getSource() == history)
		{
			changeLookAndFeel("Windows");
			hist = new History(ui.historyFile);
            
            historyFrame = new HistoryFrame(hist);
            historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			historyFrame.setLocation(this.getLocation());
	        historyFrame.setVisible(true);
	        changeLookAndFeel("Metal");
		}
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	
	private void changeLookAndFeel(String lookAndFeel)
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if (lookAndFeel.equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {}
	}
	
	public void restart()
	{
	     try
	     {
			Runtime.getRuntime().exec("java -jar Client.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	     System.exit(0);
	}
}