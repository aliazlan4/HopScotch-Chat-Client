package ui;

import java.awt.*;

import javax.swing.*;

public class UserInfoPanel extends JPanel
{
	public ChatFrame ui;
	
	public UserInfoPanel(ChatFrame ui)
	{
		this.ui = ui;
		this.setPreferredSize(new Dimension(50,0));
//		this.setBackground(new Color(0,0,0,200));
		this.setBackground(new Color(38,38,38));
	}
}