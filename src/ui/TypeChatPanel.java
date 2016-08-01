package ui;

import socket.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class TypeChatPanel extends JPanel implements KeyListener, FocusListener
{
	JTextArea chatWrite = new JTextArea(5, 20);
	String embedToChat = "";
	ChatAreaPanel chatArea;
	public ChatFrame ui;
	
	public TypeChatPanel(ChatFrame ui, ChatAreaPanel chatArea)
	{
		this.ui = ui;
		this.chatArea = chatArea;
		changeLookAndFeel("Metal");
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		chatWrite.setText("Type here...");
		chatWrite.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		chatWrite.setLineWrap(true);
		chatWrite.setWrapStyleWord(true);
		chatWrite.setOpaque(false);
		chatWrite.setForeground(Color.gray.brighter());
		chatWrite.setBackground(new Color(0,0,0,0));
		chatWrite.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		chatWrite.addKeyListener(this);
		chatWrite.addFocusListener(this);
		
		this.add(chatWrite, "Center");
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
	
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ENTER && (ke.getModifiers() & InputEvent.SHIFT_MASK) != 0)
		{
			chatWrite.append("\n\r");
		}
		else if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			embedToChat = messageAnalyzer(chatWrite.getText());
			if(embedToChat.length() > 1){
				chatArea.chatArea.append("[You > " + ui.friendListPanelDesign.jList1.getSelectedValue().toString() + "] : " + embedToChat + "\n");
				ui.client.send(new Message("message", ui.username, chatWrite.getText(), ui.friendListPanelDesign.jList1.getSelectedValue().toString()));
			}
			chatWrite.setText(null);
			chatArea.scroll.getVerticalScrollBar().setValue(chatArea.scroll.getVerticalScrollBar().getMaximum());
			ke.consume();
		}
	}
	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke) {}
	
	public void printMsg(String message){
		chatArea.chatArea.append(messageAnalyzer(message));
	}

	public String messageAnalyzer(String message)
	{
		String messageToPrint = "";
		String[] messageArrays = message.split(" ");

		for(int i=0;i<messageArrays.length; i++)
		{
			switch(messageArrays[i])
			{
			case ":)":
				messageToPrint = messageToPrint.concat(" " + "\u263A");	//done
				break;
			case "C:":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE0A");	//done
				break;
				
			case ":(":
				messageToPrint = messageToPrint.concat(" " + "\u2639");	// done
				break;
			case ":C":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE1F");	//done
				break;
			case ":'(":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE22");	//done
				break;
			case ":'C":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE2D");	//done
				break;
				
			case ":D":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE01");	//done
				break;
			case ":'D":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE02");	//done
				break;
			
			case ";)":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE09");	//done
				break;
			
			case ":|":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE11");	//done
				break;
			case ":l":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE10");	//done
				break;	
			
			case ":P":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE1B");	//done
				break;
				
			case ":p":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE1B");	//done
				break;
			case ";P":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE1C");	//done
				break;
			case ":@":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE0B");	//done
				break;	
				
			case "<3_<3":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE0D");	//done
				break;
				
			case "O:)":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE07");	//done
				break;
			
			case "3:)":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDC7F");	//done
				break;
			
			case ":L":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE12");	//done
				break;
			
			case ":/":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE16");	//done
				break;
			case ":\\":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE0F");	//done
				break;
				
			case ":o":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE2E");	//done
				break;
			case ":O":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE31");	//done
				break;
			
			case "-_-":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE2F");	//done
				break;
				
			case "B|":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE0E");	//done
				break;
			
			case ":S":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE15");	//done
				break;
			
			case "u_u":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE2A");	//done
				break;
			case "v_v":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE34");	//done
				break;
			case "U_U":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE14");	//done
				break;
				
			case "o.O":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE35");	//done
				break;
			case "O.o":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE35");	//done
				break;
			
			case ":*":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE1A");	//done
				break;
			case ";*":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE17");	//done
				break;
			
			case "D:":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE26");	//done
				break;
			case "D:<":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE20");	//done
				break;
			
			case ">,<":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE23");	//done
				break;
			case ">_<":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE27");	//done
				break;
			
			case ":3":
				messageToPrint = messageToPrint.concat(" " + "\uD83D\uDE08");	//done
				break;

			default:
				messageToPrint = messageToPrint.concat(" " + messageArrays[i]);
			}
		}
		return messageToPrint;
	}

	@Override
	public void focusGained(FocusEvent fe)
	{
		if (fe.getSource() == chatWrite && chatWrite.getText().equals("Type here..."))
			chatWrite.setText("");
	}

	@Override
	public void focusLost(FocusEvent fe)
	{
		if (fe.getSource() == chatWrite && chatWrite.getText().equals(""))
			chatWrite.setText("Type here...");
	}
}


// 33.3 x 500