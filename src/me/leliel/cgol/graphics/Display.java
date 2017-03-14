package me.leliel.cgol.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import me.leliel.cgol.Controller;

@SuppressWarnings("serial")
public class Display extends Canvas{
	
	public Display(Window window) {
		setBounds(0, 0, window.WIDTH, window.HEIGHT);
		this.addKeyListener(Controller.inputHandler);
		this.addMouseListener(Controller.inputHandler);
		this.addMouseMotionListener(Controller.inputHandler);
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		g.fillRect((int)(-InputHandler.cameraLocation.getX() + Controller.g.getRenderPos().getX()) * InputHandler.scale, 
				(int)(InputHandler.cameraLocation.getY() + Controller.g.getRenderPos().getY()) * InputHandler.scale, 
				(int)Controller.g.getDimensions().getX() * InputHandler.scale, 
				(int)Controller.g.getDimensions().getY() * InputHandler.scale);
		
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
					
					g.fillRect((int)(i-InputHandler.cameraLocation.getX() + Controller.g.getRenderPos().getX()) * InputHandler.scale, 
							(int)(j+InputHandler.cameraLocation.getY() + Controller.g.getRenderPos().getY()) * InputHandler.scale, 
							InputHandler.scale, InputHandler.scale);
				}
			}
		}
		
		g.setColor(Color.red);
		g.drawString("Camera Location: " + InputHandler.cameraLocation.x + ", " + InputHandler.cameraLocation.y, 5, 12);
		if(Controller.paused) g.drawString("Paused", 5, 30);
		
	}

}
