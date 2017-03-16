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
	
	Color[] colourList = {
			Color.black,
			Color.white,
			Color.orange,
			Color.red,
			Color.gray,
			Color.green,
			Color.darkGray,
			Color.blue,
			Color.lightGray,
			Color.pink,
			Color.yellow,
			Color.cyan,
			Color.magenta};
	
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
					g.setColor(colourList[Controller.g.cellState(i, j) % colourList.length]);
					
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
