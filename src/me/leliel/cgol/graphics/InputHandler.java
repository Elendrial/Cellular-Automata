package me.leliel.cgol.graphics;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import me.leliel.cgol.Controller;

public class InputHandler implements MouseListener, MouseMotionListener, KeyListener{
	
	public static Point cameraLocation = new Point(0,0);
	public static Point cameraMoving = new Point(0,0);
	public static int cameraSpeed = 2;
	public static int scale = 1;
	
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
		p.translate((int) (cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
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
		p.translate((int) (cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		if(Controller.paused) Controller.g.updateGrid(); // It is only necessary to update the grid if paused, if not it updates anyway.
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
}
