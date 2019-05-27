import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int xStart,yStart,xEnd,yEnd;
	
	public DrawPanel() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				xStart=mouseEvent.getX();
				yStart=mouseEvent.getY();
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {}	
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				xEnd=mouseEvent.getX();
				yEnd=mouseEvent.getY();
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(xStart, yStart, xEnd, yEnd);
		
		xStart=xEnd;
		yStart=yEnd;
	}

}
