package me.leliel.cgol.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.leliel.cgol.Controller;

@SuppressWarnings("serial")
public class Display extends Canvas implements MouseListener, MouseMotionListener, KeyListener{
	
	public Point cameraLocation = new Point(0,0);
	public Point cameraMoving = new Point(0,0);
	public int cameraSpeed = 2;
	public int scale = 1;
	
	public Display(Window window) {
		setBounds(0, 0, window.WIDTH, window.HEIGHT);
		this.addKeyListener(this);
		this.addMouseListener(this);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		g.fillRect((int)(-cameraLocation.getX() + Controller.g.getRenderPos().getX()) * scale, (int)(cameraLocation.getY() + Controller.g.getRenderPos().getY()) * scale, (int)Controller.g.getDimensions().getX() * scale, (int)Controller.g.getDimensions().getY() * scale);
		
		g.setColor(Color.white);
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				if(Controller.g.cellState(i,j))g.fillRect((int)(i-cameraLocation.getX() + Controller.g.getRenderPos().getX()) * scale, (int)(j+cameraLocation.getY() + Controller.g.getRenderPos().getY()) * scale, scale, scale);
			}
		}
		
		g.setColor(Color.red);
		g.drawString("Camera Location: " + cameraLocation.x + ", " + cameraLocation.y, 5, 12);
		if(Controller.paused) g.drawString("Paused", 5, 30);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			cameraMoving.x = -cameraSpeed;
			break;
		case 38:
			cameraMoving.y = cameraSpeed;
			break;
		case 39:
			cameraMoving.x = cameraSpeed;
			break;
		case 40:
			cameraMoving.y = -cameraSpeed;
			break;
		case KeyEvent.VK_Z:
			scale++;
//			Point p = MouseInfo.getPointerInfo().getLocation();
			
			break;
		case KeyEvent.VK_X:
			if(scale > 1) scale--;
			break;
		case KeyEvent.VK_R:
			Controller.g.randomise(0.05f);
			break;
		case KeyEvent.VK_C:
			Controller.g.clear();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			cameraMoving.x = 0;
			break;
		case 38:
			cameraMoving.y = 0;
			break;
		case 39:
			cameraMoving.x = 0;
			break;
		case 40:
			cameraMoving.y = 0;
			break;
		case KeyEvent.VK_P:
			Controller.paused = !Controller.paused;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point p = arg0.getPoint();
		p.translate((int) (this.cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-this.cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		Controller.g.setCell(!Controller.g.cellState(p), p);
		if(Controller.paused) Controller.g.updateGrid();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
