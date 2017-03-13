package me.leliel.cgol.grids;

import java.awt.Point;

public interface Grid {
	
	abstract public void setup();
	abstract public void setup(int x, int y);
	abstract public Point getDimensions();
	abstract public boolean specialOperation();
	
	// Algo stuff
	abstract public boolean cellState(int x, int y);
	abstract public boolean cellState(Point p);
	
	abstract public void setCell(boolean alive, int x, int y);
	abstract public void setCell(boolean alive, Point p);
	
	abstract public void updateGrid();
	
	abstract public void clear();
	
	abstract public void randomise(float chance);
	
	// Render stuff
	abstract public Point getRenderPos();
	
}
