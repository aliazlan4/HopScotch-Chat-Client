package ui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

public class RoundJTextField extends JTextField
{
	private Shape shape;
	public RoundJTextField(int size)
	{
		super(size);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
	}
	protected void paintBorder(Graphics g)
	{
		g.setColor(Color.BLACK);
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