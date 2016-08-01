package ui;

import socket.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.imageio.ImageIO;


public class LoginScreen extends JPanel implements MouseListener, ActionListener, FocusListener
{
	private BufferedImage background = new BufferedImage(848, 480, BufferedImage.TYPE_INT_RGB);
	private String backgroundPath = "Accessories//Backgrounds//Background.jpg";
	private JTextField username = new RoundJTextField(15);
	private JPasswordField password = new RoundJPasswordField(15, false),
			passwordBack = new RoundJPasswordField(15);
	private Color textColor = new Color(46, 80, 89);
	private JLabel register = new JLabel("register");
	private ImageIcon loginSimple = new ImageIcon("Accessories//Buttons//login.png"),
			loginHover = new ImageIcon("Accessories//Buttons//loginHover.png"),
			signup = new ImageIcon("Accessories//Buttons//signup.png"),
			signupHover = new ImageIcon("Accessories//Buttons//signupHover.png");

	private JButton login = new JButton(loginSimple),
			show = new JButton(new ImageIcon("Accessories//Buttons//show.png")),
			min = new JButton(new ImageIcon("Accessories//Buttons//minimize.png")),
			exit = new JButton(new ImageIcon("Accessories//Buttons//close.png"));
	private String screenToShow;
	
	ChatFrame ui;
	JFrame mf;
	
	public LoginScreen(ChatFrame ui)
	{
		this.ui = ui;
		setLayout(null);
		
		addMouseListener(this);
		setFocusable(true);
		
		initializeComponents();
		addListeners();
		
		add(register);
		add(username);
		add(show);
		add(password);
		add(passwordBack);
		add(min);
		add(exit);
		add(login);
		
		mf = new JFrame();
		mf.add(this);

		mf.setUndecorated(true);

		mf.setSize(848,480);
		mf.setLocationRelativeTo(null);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try
		{
			background = ImageIO.read(new File (backgroundPath));
			g.drawImage(background, 0, 0, 848,480, null);
			Thread.sleep(10);
		} catch (Exception e) {}
		repaint(100);
	}
	
	private void initializeComponents()
	{
		min.setBorderPainted(false);
		min.setFocusPainted(false);
		min.setContentAreaFilled(false);
		min.setBounds(767, 10, 30, 30);
		min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		exit.setBorderPainted(false);
		exit.setFocusPainted(false);
		exit.setContentAreaFilled(false);
		exit.setBounds(800, 10,30, 30);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		username.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		username.setText("username");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setForeground(textColor);
		username.setBounds(265,180,300,60);
		username.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		passwordBack.setText("");
		passwordBack.setBounds(265, 245, 300, 60);
		passwordBack.setFocusable(false);
		passwordBack.setEditable(false);
		
		show.setBorderPainted(false);
		show.setFocusPainted(false);
		show.setContentAreaFilled(false);
		show.setBounds(525, 265, 30, 20);
		show.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		password.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		password.setText("password");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setForeground(textColor);
		password.setBounds(299, 245, 225, 60);
		password.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		password.setEchoChar((char)0);

		login.setBorderPainted(false);
		login.setFocusPainted(false);
		login.setContentAreaFilled(false);
		login.setBounds(270, 310, 300, 60);
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		register.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		register.setHorizontalAlignment(SwingConstants.CENTER);
		register.setForeground(new Color(20, 35, 35));
		register.setBounds(380, 365, 80, 30);
		register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	private void addListeners()
	{
		username.addActionListener(this);
		password.addActionListener(this);

		register.addMouseListener(this);
		username.addMouseListener(this);
		password.addMouseListener(this);
		min.addMouseListener(this);
		exit.addMouseListener(this);
		login.addMouseListener(this);
		show.addMouseListener(this);

		username.addFocusListener(this);
		password.addFocusListener(this);
	}
	
	public void focusGained(FocusEvent fe)
	{
		if (fe.getSource() == username && username.getText().equals("username"))
			username.setText("");
		if (fe.getSource() == password && password.getText().equals("password"))
		{
			password.setEchoChar('\u25CF');
			password.setText("");
		}
	}
	public void focusLost(FocusEvent fe)
	{
		if (fe.getSource() == username && username.getText().equals(""))
			username.setText("username");
		if (fe.getSource() == password && password.getText().equals(""))
		{
			password.setEchoChar((char)0);
			password.setText("password");
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if((ae.getSource() == username || ae.getSource() == password) && register.getText().equals("register"))
		{
			if((!(username.getText().equals("") || password.getText().equals(""))) && (!(username.getText().equals("username")))
					&& (!(password.getText().equals("password"))))
			{
				ui.username = username.getText();
				ui.password = password.getText();
				ui.client.send(new Message("login", ui.username, ui.password, "SERVER"));
			}
			else
			{
				changeLookAndFeel("Windows");
				JOptionPane.showMessageDialog(this, "Please Enter the Correct Combination of Username and Password.", "Error!", JOptionPane.ERROR_MESSAGE);
				changeLookAndFeel("Metal");
			}
		}
		else
		{
			if (register.getText().equals("register"))
			{
				if((!(username.getText().equals("") || password.getText().equals(""))) && (!(username.getText().equals("username")))
						&& (!(password.getText().equals("password"))))
				{
					// forgot what goes here
				}
				else
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "Please Enter the Correct Combination of Username and Password.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				}
			}
			else
			{
				if(username.getText().equals("") || password.getText().equals(""))
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "Please Enter the Correct Combination of Username and Password.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				}
				else if(username.getText().equals("admin") && password.getText().equals("admin"))
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "That Combination of Username and Password is Reserved for Admin Only.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				}
				else if(!(username.getText().equals("")|| password.getText().equals("")))
				{
					ui.username = username.getText();
					ui.password = password.getText();
					ui.client.send(new Message("signup", ui.username, ui.password, "SERVER"));
				}
			}
		}
	}

	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == exit)
		{
			changeLookAndFeel("Windows");
			JOptionPane.showMessageDialog(this, "Bye Bye :)","Exit", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		}
		if (me.getSource() == min)
			((JFrame) SwingUtilities.getWindowAncestor(this)).setState(Frame.ICONIFIED);
		
		if (me.getSource() == this)
		{
			this.grabFocus();
			if(username.getText().equals(""))
				username.setText("username");
			if(password.getText().equals(""))
			{
				password.setEchoChar((char)0);
				password.setText("password");
			}
		}
		if (me.getSource() == register)
		{
			registerClicked();
		}
		if (me.getSource() == login)
		{
			if (register.getText().equals("register"))
			{
				if((!(username.getText().equals("") || password.getText().equals(""))) 
						&& (!(username.getText().equals("username"))) && (!(password.getText().equals("password"))))
				{
					ui.username = username.getText();
					ui.password = password.getText();
					ui.client.send(new Message("login", ui.username, ui.password, "SERVER"));
				}
				else
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "Please Enter the Correct Combination of Username and Password.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				}
			}
			else
			{
				if(username.getText().equals("") || password.getText().equals(""))
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "Please Enter the Correct Combination of Username and Password.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				} else if(username.getText().equals("admin") && password.getText().equals("admin"))
				{
					changeLookAndFeel("Windows");
					JOptionPane.showMessageDialog(this, "That Combination of Username and Password is Reserved for Admin Only.", "Error!", JOptionPane.ERROR_MESSAGE);
					changeLookAndFeel("Metal");
				} else if(!(username.getText().equals("") || password.getText().equals(""))) {
					ui.username = username.getText();
					ui.password = password.getText();
					ui.client.send(new Message("signup", ui.username, ui.password, "SERVER"));
				}
			}
		}
	}
	
	public void loginError(){
		JOptionPane.showMessageDialog(this, "Username or Password is incorrect!", "Login Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void logicSuccess(){
		mf.removeAll();
		mf.dispose();
		ui.mf.setVisible(true);
	}
	
	public void signupError()
	{
		changeLookAndFeel("Windows");
		JOptionPane.showMessageDialog(this, "Username already exist!!", "Login Error!", JOptionPane.ERROR_MESSAGE);
		changeLookAndFeel("Metal");
	}
	
	public void mouseEntered(MouseEvent me)
	{
		if (me.getSource() == login && login.getIcon() == loginSimple)
		{
			login.setIcon(loginHover);
		} else if(me.getSource() == login && login.getIcon() == signup)
		{
			login.setIcon(signupHover);
		}
	}	
	public void mouseExited(MouseEvent me)
	{
		if (me.getSource() == login && login.getIcon() == loginHover)
		{
			login.setIcon(loginSimple);
		} else if(me.getSource() == login && login.getIcon() == signupHover)
		{
			login.setIcon(signup);
		}
	}
	public void mousePressed(MouseEvent me)
	{
		if (me.getSource() == show)
			password.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		if (me.getSource() == show && (!(password.getText().equals("password"))))
			password.setEchoChar('\u25CF');
	}

	private void showRegister()
	{
		username.setText("username");
		password.setEchoChar((char)0);
		password.setText("password");

		register.setText("login");
		login.setIcon(signup);

		register.setBounds(380, 365, 80, 30);
		
		username.setBounds(265,180,300,60);
		username.setVisible(true);

		password.setBounds(299, 245, 225, 60);
		password.setVisible(true);

		passwordBack.setBounds(265, 245, 300, 60);
		passwordBack.setVisible(true);

		login.setBounds(270, 310, 300, 60);

		show.setBounds(525, 265, 30, 20);
		show.setVisible(true);
	}
	private void showLogin()
	{
		username.setText("username");
		password.setEchoChar((char)0);
		password.setText("password");

		username.setText("username");
		
		password.setEchoChar((char)0);
		password.setText("password");

		register.setText("register");
		login.setIcon(loginSimple);

		register.setBounds(380, 365, 80, 30);
		
		username.setBounds(265,180,300,60);
		username.setVisible(true);

		password.setBounds(299, 245, 225, 60);
		password.setVisible(true);

		passwordBack.setBounds(265, 245, 300, 60);
		passwordBack.setVisible(true);

		login.setBounds(270, 310, 300, 60);

		show.setBounds(525, 265, 30, 20);
		show.setVisible(true);
	}
	private void registerClicked()
	{
		if (register.getText().equals("register"))
		{
			screenToShow = "Sign Up";
			showRegister();
		} else
		{
			screenToShow = "Login";
			showLogin();
		}
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