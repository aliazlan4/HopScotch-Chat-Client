package ui;


import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class ChatAreaPanel extends JPanel
{
	public JTextArea chatArea = new JTextArea("\n");
	JScrollPane scroll;
	public ChatFrame ui;
	
	public ChatAreaPanel(ChatFrame ui)
	{
		this.ui = ui;
		
		changeLookAndFeel("Nimbus");
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0,0,0,150));
		this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		
		chatArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		chatArea.setOpaque(false);
		chatArea.setBackground(Color.WHITE);
		chatArea.setForeground(Color.gray.brighter());
		chatArea.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		
		scroll = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		scroll.getViewport().setOpaque(false);
		scroll.setViewportBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		scroll.setAutoscrolls(true);
		
		add(scroll, "Center");
	}
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
}