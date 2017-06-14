package me.leliel.cgol.graphics;

import java.awt.Color;
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
		case KeyEvent.VK_ESCAPE:
			doOptionsMenu();
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
	
	
	// Probably an awful way to do it, but hey, it's quick and simple!
	public void doOptionsMenu(){
		Object[] options = {"Algorithm", "Tick rate", "Mouse Options", "Display Options", "Cancel"};
		
		int initial = JOptionPane.showOptionDialog(null, "What would you like to change?", "Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		// Algorithm
		if(initial == 0){
			Controller.changeAlgorithm(JOptionPane.showInputDialog(null, "What would you like to do?", "TSP Node Options"));
		}
		
		// DELAY
		if(initial == 1){
			Controller.tickControl.VARIABLE_TARGET_TPS = Integer.parseInt(JOptionPane.showInputDialog(null, "Current TPS: " + Controller.tickControl.VARIABLE_TARGET_TPS + "\nSet new Tick Rate"));
			Controller.tickControl.updateTPS();
		}
		
		// Mouse Options
		if(initial == 2){
			Object[] mouseOptions = {"Left Click Action", "Middle Click Action", "Right Click Action", "Cancel"};
			
			int mouseOption =  JOptionPane.showOptionDialog(null, "What would you like to change?", "Mouse Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, mouseOptions, mouseOptions[0]);
			
			switch(mouseOption){
			case 0:
				leftClickAction = Integer.parseInt(JOptionPane.showInputDialog(null, "What state should left click set cells to? (-1 for incrementing)"));
				break;
			case 1:
				middleClickAction = Integer.parseInt(JOptionPane.showInputDialog(null, "What state should middle click set cells to? (-1 for incrementing)"));
				break;
			case 2:
				rightClickAction = Integer.parseInt(JOptionPane.showInputDialog(null, "What state should right click set cells to? (-1 for incrementing)"));
				break;
			}
		}
		
		// Display Options
		if(initial == 3){
			Object[] displayOptions = {"State colours", "Zoom in key", "Zoom out key", "Screen Info", "Cancel"};
			
			int displayOption =  JOptionPane.showOptionDialog(null, "What would you like to change?", "Display Options", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, displayOptions, displayOptions[0]);
			
			switch(displayOption){
			case 0:
				int state = Integer.parseInt(JOptionPane.showInputDialog(null, "What state do you want to change the colour of?"));
				Color c = Color.getColor(JOptionPane.showInputDialog(null, "What is the new colour?"));
				System.out.println(c.toString());
				Controller.win.display.colourList[state % 12] = c;
			}
		}
	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
