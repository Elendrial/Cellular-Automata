package me.leliel.cgol.algorithms;

import me.leliel.cgol.Controller;

public class WackyTest implements Algorithm {

	/*
	 * Just used for testing and as an additional example for how to setup a new algorithm, not to be taken as an actual
	 */
	
	@Override
	public void tick() {
		int count = 0;
		for(int i = 0; i < Controller.g.getDimensions().getX() && !Controller.g.isBusy(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY() && !Controller.g.isBusy(); j++){
				count = 0;
				
				if(Controller.g.cellState(i, j) == 4 && i < Controller.g.getDimensions().getX() -1 && j < Controller.g.getDimensions().getY() -1 
															&& i > Controller.g.getDimensions().getX() + 1 && j > Controller.g.getDimensions().getY() +1) 
					for(int x = -1; x < 2; x++) for(int y = -1; y < 2; y++) Controller.g.setCell(0, i+x,j+y);
				
				for(int x = -2; x < 2; x++){
					for(int y = 0; y < 3; y++){
						if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y) == 1) count++;
						else if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y) == 2) count -= 2;
						else if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y) == 3) count -= 3;
					}
				}
				if(count < 3) Controller.g.setCell(Controller.g.cellState(i, j) >0 ? Controller.g.cellState(i, j)-1 : 0 ,i,j);
				else if(count < 6) Controller.g.setCell(3,i,j);
				else if(count < 7) Controller.g.setCell(0,i,j);
				else if(count < 8) Controller.g.setCell(4,i,j);
				else Controller.g.setCell(Controller.g.cellState(i, j) >0 ? Controller.g.cellState(i, j)-1 : 0 ,i,j);
			}
		}
		
		if(!Controller.g.isBusy()) Controller.g.updateGrid();
		
	}

	@Override
	public int maxState() {
		return 4;
	}

}
