package me.leliel.cgol.algorithms;

import me.leliel.cgol.Controller;

public class StandardGoL implements Algorithm{

	@Override
	public void tick() {
		int count = 0;
		for(int i = 0; i < Controller.g.getDimensions().getX() && !Controller.g.specialOperation(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY() && !Controller.g.specialOperation(); j++){
				count = 0;
				for(int x = -1; x <2; x++){
					for(int y = -1; y < 2; y++){
						if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y)) count++;
					}
				}
				
				Controller.g.setCell((Controller.g.cellState(i,j) && count == 2) || count == 3, i,j);
			}
		}
		
		if(!Controller.g.specialOperation()) Controller.g.updateGrid();
		
	}
	
	
	
}
