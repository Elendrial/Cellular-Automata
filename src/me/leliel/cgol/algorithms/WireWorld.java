package me.leliel.cgol.algorithms;

import me.leliel.cgol.Controller;
import me.leliel.cgol.grids.Grid;

public class WireWorld implements Algorithm {

	@Override
	public void tick() {
		for(int i = 0; i < Controller.g.getDimensions().getX() && !Controller.g.isBusy(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY() && !Controller.g.isBusy(); j++){
				if(Controller.g.cellState(i, j) == 1){
					int count = 0;
					for(int x = -1; x <2; x++){
						for(int y = -1; y < 2; y++){
							if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y) == 3) count++;
						}
					}
					
					if(count >= 1 && count < 3) Controller.g.setCell(3, i, j);
				}
				else if(Controller.g.cellState(i, j) != 0) Controller.g.setCell(Controller.g.cellState(i, j)-1, i, j);
				
			}
		}
		
		if(!Controller.g.isBusy()) Controller.g.updateGrid();

	}

	@Override
	public int maxState() {
		return 3;
	}

	@Override
	public String algorithmName() {
		return "Wire World";
	}

	@Override
	public void randomizeGrid(Grid g) {
		//TODO
	}

}
