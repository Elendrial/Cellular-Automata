package me.leliel.cgol;

import java.util.ArrayList;
import java.util.Random;

import me.leliel.cgol.algorithms.Algorithm;
import me.leliel.cgol.graphics.InputHandler;
import me.leliel.cgol.graphics.Window;
import me.leliel.cgol.grids.Grid;

public class Controller implements Runnable{
	public static Algorithm alg;
	public static Grid g;
	public static Window win;
	public static Controller tickControl = new Controller();
	public static InputHandler inputHandler = new InputHandler();
	
	public static ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
	public static Random rand = new Random();
	
	public static boolean paused = false;
	public static boolean isRunning = false;
	
	public static void setup(){g.setup();}
	
	public static void setup(int x, int y){g.setup(x ,y);}
	
	public static void start(){
		isRunning = true;
		win.start();
        new Thread(tickControl).start();
	}
	
	public static void tick(){
		if(!paused) alg.tick();
	}
	
	public static void tickFixed(){
		InputHandler.cameraLocation.translate(InputHandler.cameraMoving.x, InputHandler.cameraMoving.y);
	}

	public static void changeAlgorithm(String newAlg){
		String s = newAlg.trim().toLowerCase().replace(" ", "");
		for(int i = 0; i < algorithms.size(); i++){
			if(algorithms.get(i).algorithmName().trim().toLowerCase().replace(" ", "").equals(s)){
				alg = algorithms.get(i);
			}
		}
	}
	
	
	// How often we want the game to tick per second
    public int VARIABLE_TARGET_TPS = 30;
    public int FIXED_TARGET_TPS = 30;

    private double nsPerTick1, nsPerTick2;
    
    public void updateTPS(){
    	double secondsPerTick = 1D / VARIABLE_TARGET_TPS;
        nsPerTick1 = secondsPerTick * 1000000000D;
        
        secondsPerTick = 1D / FIXED_TARGET_TPS;
        nsPerTick2 = secondsPerTick * 1000000000D;
    }
    
	@Override
	public void run() {
        int tick = 0;
        
        updateTPS();
        
        double fpsTimer = System.currentTimeMillis();
        double then1 = System.nanoTime();
        double now1;
        double unprocessed1 = 0;

        
        double then2 = System.nanoTime();
        double now2;
        double unprocessed2 = 0;
        
        while(isRunning){
            // Variable ticks (algorithm)
        	now1 = System.nanoTime();
            unprocessed1 += (now1 - then1) / nsPerTick1;
            then1 = now1;
            while(unprocessed1 >= 1 && System.currentTimeMillis() - fpsTimer < 1000){
                tick();
                tick++;
                unprocessed1--;
            }
            
            // Fixed ticks (camera moving)
            now2 = System.nanoTime();
            unprocessed2 += (now2 - then2) / nsPerTick2;
            then2 = now2;
            while(unprocessed2 >= 1){
                tickFixed();
                unprocessed2--;
            }
            
            // This is NOT to sleep, but to limit the game loop
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // If the current time is 1 second greater than the last time we printed
            if(System.currentTimeMillis() - fpsTimer >= 1000){
                System.out.printf("FPS: %d, TPS: %d%n", Controller.win.fps, tick);
                Controller.win.fps = 0;
                tick = 0;
                fpsTimer += 1000;
            }
        }

		
	}
	
	
}
