package me.leliel.cgol.grids;

import java.awt.Point;

import me.leliel.cgol.Controller;

public class StandardGrid implements Grid{

	public int[][] grid;
	public int[][] upcomingGrid;
	
	public Point dimensions;
	public Point renderPoint;
	
	public boolean specialOperation = false;
	
	public void setup(){
		this.setup(100, 100);
	}
	
	@SuppressWarnings("unused")
	public void setup(int x, int y){
		grid = new int[x][y];
		
		for(int[] b : grid)	for(int b2 : b)	b2 = 0;
		
		upcomingGrid = new int[x][y];
		
		dimensions = new Point(x,y);
		
		renderPoint = new Point((Controller.win.WIDTH - x)/2, (Controller.win.HEIGHT - y)/2);
	}
	
	public Point getDimensions(){
		return dimensions;
	}
	
	public boolean isBusy(){
		return specialOperation;
	}
	
	// ALGO
	
	@Override
	public int cellState(int x, int y) {
		try{return grid[x][y];}
		catch(Exception e){return 0;}
	}
	
	@Override
	public int cellState(Point p) {
		try{return grid[p.x][p.y];}
		catch(Exception e){return 0;}
	}
	
	
	public void setCell(int state, int x, int y){
		try{upcomingGrid[x][y] = state;}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void setCell(int state, Point p){
		try{upcomingGrid[p.x][p.y] = state;}
		catch(Exception e){e.printStackTrace();}
	}
	
	
	@SuppressWarnings("unused")
	public void updateGrid(){
		for(int i = 0; i < grid.length; i++){
			grid[i] = upcomingGrid[i].clone();
			for(int b : upcomingGrid[i]) b = 0;
		}
	}
	
	public void clear(){
		this.specialOperation = true;
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				this.setCell(0, i,j);
			}
		}
		this.updateGrid();
		this.specialOperation = false;
	}
	
	public void randomise(float... chance){
		this.specialOperation = true;
		float total = 0;
		float[] chances = chance;
		
		for(int i = 0; i < chances.length; i++) total += chances[i];
		
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				final float f = Controller.rand.nextFloat();
				float temp = f * total;
				boolean found = false;
				for(int k = 0; k < chances.length && !found; k++){
					temp -= chances[k];
					if(temp <= 0){
						this.setCell(k, i,j);
						found = true;
					}
				}
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
