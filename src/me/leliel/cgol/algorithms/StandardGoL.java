package me.leliel.cgol.algorithms;

import me.leliel.cgol.Controller;
import me.leliel.cgol.grids.Grid;

public class StandardGoL implements Algorithm{

	@Override
	public void tick() {
		int count = 0;
		for(int i = 0; i < Controller.g.getDimensions().getX() && !Controller.g.isBusy(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY() && !Controller.g.isBusy(); j++){
				count = 0;
				for(int x = -1; x <2; x++){
					for(int y = -1; y < 2; y++){
						if((x!=0 || y!=0) && Controller.g.cellState(i+x, j+y) == 1) count++;
					}
				}
				
				Controller.g.setCell(((Controller.g.cellState(i,j) == 1 && count == 2) || count == 3) ? 1 : 0 , i,j);
			}
		}
		
		if(!Controller.g.isBusy()) Controller.g.updateGrid();
		
	}

	@Override
	public int maxState() {
		return 1;
	}

	@Override
	public String algorithmName() {
		return "Game Of Life";
	}


	public float[] chances = {0.95f, 0.5f};
	
	@Override
	public void randomizeGrid(Grid g) {
		g.setBusy(true);
		float total = 0;
		
		for(int i = 0; i < chances.length; i++) total += chances[i];
		
		for(int i = 0; i < Controller.g.getDimensions().getX(); i++){
			for(int j = 0; j < Controller.g.getDimensions().getY(); j++){
				final float f = Controller.rand.nextFloat();
				float temp = f * total;
				boolean found = false;
				for(int k = 0; k < chances.length && !found; k++){
					temp -= chances[k];
					if(temp <= 0){
						g.setCell(k, i,j);
						found = true;
					}
				}
			}
		}
		
		g.updateGrid();
		g.setBusy(false);
	}
	
	
	
}
