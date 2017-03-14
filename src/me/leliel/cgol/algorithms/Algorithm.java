package me.leliel.cgol.algorithms;

import me.leliel.cgol.grids.Grid;

public interface Algorithm {
	abstract public void tick();
	abstract public int maxState();
	abstract public String algorithmName();
	abstract public void randomizeGrid(Grid g);
}
