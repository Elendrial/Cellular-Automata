package me.leliel.cgol.grids;

import java.awt.Point;

public interface Grid {
	
	abstract public void setup();
	abstract public void setup(int x, int y);
	abstract public Point getDimensions();
	abstract public boolean isBusy();
	
	// Algorithm stuff
	abstract public int cellState(int x, int y);
	abstract public int cellState(Point p);
	
	abstract public void setCell(int state, int x, int y);
	abstract public void setCell(int state, Point p);
	
	abstract public void updateGrid();
	
	abstract public void clear();
	
	abstract public void randomise(float... chance);
	
	// Render stuff
	abstract public Point getRenderPos();
	
}
