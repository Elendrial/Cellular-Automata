package me.leliel.cgol;

import me.leliel.cgol.algorithms.Algorithm;
import me.leliel.cgol.graphics.Window;
import me.leliel.cgol.grids.Grid;

public class Controller implements Runnable{
	public static Algorithm alg;
	public static Grid g;
	public static Window win;
	public static Controller tickControl = new Controller();
	
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
		win.display.cameraLocation.translate(win.display.cameraMoving.x, win.display.cameraMoving.y);
	}

	
	
	// How often we want the game to tick per second
    public int VARIABLE_TARGET_TPS = 30;
    public int FIXED_TARGET_TPS = 30;
    
	@Override
	public void run() {
        int tick = 0;
        
        double fpsTimer = System.currentTimeMillis();
        double secondsPerTick = 1D / VARIABLE_TARGET_TPS;
        double nsPerTick1 = secondsPerTick * 1000000000D;
        double then1 = System.nanoTime();
        double now1;
        double unprocessed1 = 0;

        secondsPerTick = 1D / FIXED_TARGET_TPS;
        double nsPerTick2 = secondsPerTick * 1000000000D;
        double then2 = System.nanoTime();
        double now2;
        double unprocessed2 = 0;
        
        while(isRunning){
           // Variable ticks (algorithm)
        	now1 = System.nanoTime();
            unprocessed1 += (now1 - then1) / nsPerTick1;
            then1 = now1;
            while(unprocessed1 >= 1){
                tick();
                tick++;
                unprocessed1--;
            } tick(); tick++;
            
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
