package ui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;

public class RoundJPasswordField extends JPasswordField
{
	private Shape shape;
	private boolean border = true;
	public RoundJPasswordField(int size)
	{
		super(size);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
	}
	public RoundJPasswordField(int size, boolean flag)
	{
		super(size);
		border = flag;
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
	}
	protected void paintBorder(Graphics g)
	{
		g.setColor(Color.black);
		if (border)
			g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
	}
	public boolean contains(int x, int y)
	{
		if (shape == null || !shape.getBounds().equals(getBounds()))
		{
			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 50, 50);
		}
		return shape.contains(x, y);
	}
}