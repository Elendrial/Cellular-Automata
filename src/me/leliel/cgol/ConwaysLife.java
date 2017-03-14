package me.leliel.cgol;

import me.leliel.cgol.algorithms.StandardGoL;
import me.leliel.cgol.algorithms.WackyTest;
import me.leliel.cgol.algorithms.WireWorld;
import me.leliel.cgol.graphics.Window;
import me.leliel.cgol.grids.StandardGrid;

public class ConwaysLife {
	
	public static void main(String[] args){
		init();
		Controller.start();
		
	}
	
	public static void init(){
		Controller.algorithms.add(new StandardGoL());
		Controller.algorithms.add(new WireWorld());
		Controller.algorithms.add(new WackyTest());
		
		Controller.alg = new WireWorld();
		Controller.g = new StandardGrid();
		
		Controller.win = new Window("Conways game of Life", 1100, 650);
		Controller.setup(1150, 675);
	}
	
}
