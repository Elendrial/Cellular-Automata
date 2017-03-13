package me.leliel.cgol.grids;

import java.awt.Point;
import java.util.Random;

import me.leliel.cgol.Controller;

public class StandardGrid implements Grid{

	public boolean[][] grid;
	public boolean[][] upcomingGrid;
	
	public Point dimensions;
	public Point renderPoint;
	
	public boolean specialOperation = false;
	
	public void setup(){
		this.setup(100, 100);
	}
	
	@SuppressWarnings("unused")
	public void setup(int x, int y){
		grid = new boolean[x][y];
		
		for(boolean[] b : grid)	for(boolean b2 : b)	b2 = false;
		
		upcomingGrid = new boolean[x][y];
		
		dimensions = new Point(x,y);
		
		renderPoint = new Point((Controller.win.WIDTH - x)/2, (Controller.win.HEIGHT - y)/2);
	}
	
	public Point getDimensions(){
		return dimensions;
	}
	
	public boolean specialOperation(){
		return specialOperation;
	}
	
	// ALGO
	
	@Override
	public boolean cellState(int x, int y) {
		try{return grid[x][y];}
		catch(Exception e){return false;}
	}
	
	@Override
	public boolean cellState(Point p) {
		try{return grid[p.x][p.y];}
		catch(Exception e){return false;}
	}
	
	
	public void setCell(boolean alive, int x, int y){
		try{upcomingGrid[x][y] = alive;}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void setCell(boolean alive, Point p){
		try{upcomingGrid[p.x][p.y] = alive;}
		catch(Exception e){e.printStackTrace();}
	}
	
	
	@SuppressWarnings("unused")
	public void updateGrid(){
		for(int i = 0; i < grid.length; i++){
			grid[i] = upcomingGrid[i].clone();
			for(boolean b : upcomingGrid[i]) b = false;
		}
	}
	
	public void clear(){
		this.specialOperation = true;
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				this.setCell(false, i,j);
			}
		}
		this.updateGrid();
		this.specialOperation = false;
	}
	
	public void randomise(float chance){
		this.specialOperation = true;
		Random rand = new Random();
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				if(rand.nextFloat() < chance) this.setCell(true, i,j);
			}
		}
		this.updateGrid();
		this.specialOperation = false;
	}
	
	// RENDER
	
	public Point getRenderPos(){
		return renderPoint;
	}
}
