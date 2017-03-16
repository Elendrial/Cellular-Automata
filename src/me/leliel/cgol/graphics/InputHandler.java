package me.leliel.cgol.graphics;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
			Controller.g.randomise();
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
	public void mouseClicked(MouseEvent arg0) {
		if(SwingUtilities.isLeftMouseButton(arg0)) leftClick(arg0);
		else if(SwingUtilities.isRightMouseButton(arg0)) rightClick(arg0);
		else if(SwingUtilities.isMiddleMouseButton(arg0)) middleClick(arg0);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(SwingUtilities.isLeftMouseButton(arg0)) leftClick(arg0);
		else if(SwingUtilities.isRightMouseButton(arg0)) rightClick(arg0);
		else if(SwingUtilities.isMiddleMouseButton(arg0)) middleClick(arg0);
	}

	
	public static int leftClickAction = -1; // -1 : increment cell state, anything else : set cell state to x
	public static int rightClickAction = 0;
	public static int middleClickAction = 1;
	
	public void leftClick(MouseEvent arg0){
		Point p = arg0.getPoint();
		p.translate((int) (cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		if(leftClickAction <= -1) Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		else Controller.g.setCell(leftClickAction, p);
		if(Controller.paused) Controller.g.updateGrid(); // It is only necessary to update the grid if paused, if not it updates anyway.
	}
	

	public void middleClick(MouseEvent arg0){
		Point p = arg0.getPoint();
		p.translate((int) (cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		if(middleClickAction <= -1) Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		else Controller.g.setCell(middleClickAction, p);
		if(Controller.paused) Controller.g.updateGrid(); // It is only necessary to update the grid if paused, if not it updates anyway.
	}
	
	
	public void rightClick(MouseEvent arg0){
		Point p = arg0.getPoint();
		p.translate((int) (cameraLocation.getX() - Controller.g.getRenderPos().getX())*scale, (int)(-cameraLocation.getY() - Controller.g.getRenderPos().getY())*scale);
		
		p.setLocation(p.x/scale, p.y/scale);
		
		if(rightClickAction <= -1) Controller.g.setCell(Controller.g.cellState(p) == Controller.alg.maxState() ? 0 : Controller.g.cellState(p) + 1, p);
		else Controller.g.setCell(rightClickAction, p);
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

}
