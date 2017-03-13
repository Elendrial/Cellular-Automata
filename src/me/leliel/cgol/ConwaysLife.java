package me.leliel.cgol;

import me.leliel.cgol.algorithms.StandardGoL;
import me.leliel.cgol.graphics.Window;
import me.leliel.cgol.grids.StandardGrid;

public class ConwaysLife {
	
	public static void main(String[] args){
		Controller.alg = new StandardGoL();
		Controller.g = new StandardGrid();
		
		Controller.win = new Window("Conways game of Life", 1100, 650);
		Controller.setup(1150, 675);
		Controller.start();
		
	}
	
}
