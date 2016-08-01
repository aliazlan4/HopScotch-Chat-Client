package ui;
import javax.swing.*;

public class SplashScreen extends JPanel implements Runnable
{
	private ImageIcon splashLoad;
	private JLabel splashLoading;
	private String backgroundPath = "Accessories//Backgrounds//SplashScreen.gif";
	ChatFrame ui;
	
	public SplashScreen(ChatFrame ui)
	{
		this.ui = ui;
		this.setLayout(null);
		try
		{
			splashLoad = new ImageIcon(backgroundPath);
			splashLoading = new JLabel(splashLoad);
			splashLoading.setBounds(0,0,720,405);
			this.add(splashLoading);
		} catch (Exception e) {}
	}
	
	public void run(){
		JFrame mf = new JFrame();
		
		mf.add(this);
		mf.setUndecorated(true);
		mf.setSize(720, 405);
		mf.setLocationRelativeTo(null);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		closeSplashScreen(300, mf);
	}

	private void closeSplashScreen(int falseTime, JFrame mf)
	{
		for(int i=0;i<falseTime;i++)
		{
			try{
				Thread.sleep(10);
			} catch(Exception e){}
		}
		mf.removeAll();
		mf.dispose();
		ui.showLoginScreen();
	}
}