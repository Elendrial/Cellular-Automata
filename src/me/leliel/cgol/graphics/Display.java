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
		this.addMouseMotionListener(this);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		g.fillRect((int)(-cameraLocation.getX() + Controller.g.getRenderPos().getX()) * scale, (int)(cameraLocation.getY() + Controller.g.getRenderPos().getY()) * scale, (int)Controller.g.getDimensions().getX() * scale, (int)Controller.g.getDimensions().getY() * scale);
		
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				if(Controller.g.cellState(i, j) != 0){
					if(Controller.g.cellState(i,j) == 1) g.setColor(Color.white);
					else if(Controller.g.cellState(i,j) == 2)g.setColor(Color.pink);
					else if(Controller.g.cellState(i,j) == 3)g.setColor(Color.blue);
					else if(Controller.g.cellState(i,j) == 4)g.setColor(Color.red);
					else if(Controller.g.cellState(i,j) == 5)g.setColor(Color.GREEN);
					else if(Controller.g.cellState(i,j) == 6)g.setColor(Color.gray);
					else if(Controller.g.cellState(i,j) == 7)g.setColor(Color.orange);
					else if(Controller.g.cellState(i,j) == 8)g.setColor(Color.darkGray);
					else if(Controller.g.cellState(i,j) == 9)g.setColor(Color.yellow);
					else if(Controller.g.cellState(i,j) == 10)g.setColor(Color.LIGHT_GRAY);
					else if(Controller.g.cellState(i,j) == 11)g.setColor(Color.cyan);
					else if(Controller.g.cellState(i,j) == 12)g.setColor(Color.magenta);
					else if(Controller.g.cellState(i,j) == 13)g.setColor(Color.pink);
					
					g.fillRect((int)(i-cameraLocation.getX() + Controller.g.getRenderPos().getX()) * scale, (int)(j+cameraLocation.getY() + Controller.g.getRenderPos().getY()) * scale, scale, scale);
				}
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
			Controller.g.randomise(0.95f, 0.05f);
			break;
		case KeyEvent.VK_C:
			Controller.g.clear();
			break;
		case KeyEvent.VK_T:
			if(Controller.paused) Controller.alg.tick();
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
		
		Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		if(Controller.paused) Controller.g.updateGrid(); // It is only necessary to update the grid if paused, if not it updates anyway.
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
	public void mouseDragged(MouseEvent arg0) {
		Point p = arg0.getPoint();
		p.translate((int) (this.cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-this.cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		if(Controller.paused) Controller.g.updateGrid(); // It is only necessary to update the grid if paused, if not it updates anyway.
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
