package me.leliel.cgol.grids;

import java.awt.Point;

import me.leliel.cgol.Controller;

public interface Grid {
	
	abstract public void setup();
	abstract public void setup(int x, int y);
	abstract public Point getDimensions();
	abstract public boolean isBusy();
	abstract public void setBusy(boolean b);
	
	// Algorithm stuff
	abstract public int cellState(int x, int y);
	abstract public int cellState(Point p);
	
	abstract public void setCell(int state, int x, int y);
	abstract public void setCell(int state, Point p);
	
	abstract public void updateGrid();
	
	abstract public void clear();
	
	public default void randomise(){
		Controller.alg.randomizeGrid(this);
	}
	
	// Render stuff
	abstract public Point getRenderPos();
	
}
