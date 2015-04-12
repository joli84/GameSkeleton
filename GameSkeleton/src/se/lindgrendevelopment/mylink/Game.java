package se.lindgrendevelopment.mylink;

import java.awt.Canvas;

import se.lindgrendevelopment.mylink.utils.Commons;

public class Game extends Canvas implements Runnable 
{
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private InputHandler input = new InputHandler(this);
	
	private boolean running;
	private int tickCount = 0;
	
	/**
	 * Constuctor
	 */
	public Game()
	{
		
	}
	
	/**
	 * Init game
	 */
	private void init()
	{
		System.out.println("Init!");
	}
	
	/**
	 * Start thread
	 */
	public void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Stop thread
	 */
	public void stop()
	{
		running = false;
	}
	
	/**
	 * Game loop
	 */
	@Override
	public void run() 
	{
		System.out.println("Thread running!");
		init();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = Commons.AMMOUNT_OF_TICKS;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				updates = 0;
				frames = 0;
			}
		}
	}
	
	private void tick()
	{
//		System.out.println("Tick!");
		tickCount++;
		if(!hasFocus())
		{
			input.releaseAll();
		}else
		{
			input.tick();
		}
	}
	
	private void render()
	{
//		System.out.println("Render!");
	}
	
	
	
	public static void main(String args[])
	{
		Window window = new Window(Commons.WINDOW_WIDTH * Commons.WINDOW_SCALE, 
				Commons.WINDOW_HEIGHT * Commons.WINDOW_SCALE, Commons.TITLE, new Game());
	}

}
