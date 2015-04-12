package se.lindgrendevelopment.mylink;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class InputHandler implements KeyListener
{
	/**
	 * Internal key class
	 *
	 */
	public class Key 
	{
		public int presses, absorbs;
		public boolean down, clicked;
		
		public Key()
		{
			keys.add(this);
		}
		
		public void toggle(boolean pressed) 
		{
//			System.out.println("Key pressed");
			if (pressed != down) 
			{
				down = pressed;
			}
			if (pressed) 
			{
				presses++;
			}
		}

		public void tick() 
		{
			if (absorbs < presses) 
			{
				absorbs++;
				clicked = true;
			} else 
			{
				clicked = false;
			}
		}
		
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	/**
	 * Constructor
	 * @param game
	 */
	public InputHandler(Game game)
	{
		game.addKeyListener(this);
	}

	public void releaseAll() 
	{
//		System.out.println("Lost focus!");
		for (int i = 0; i < keys.size(); i++) 
		{
			keys.get(i).down = false;
		}
	}

	public void tick() 
	{
		for (int i = 0; i < keys.size(); i++) 
		{
			keys.get(i).tick();
		}
	}
	
	/**
	 * Trigged when key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent keyevent) 
	{
		toggle(keyevent, true);
	}

	@Override
	public void keyReleased(KeyEvent keyevent) 
	{
		toggle(keyevent, false);
	}

	@Override
	public void keyTyped(KeyEvent keyevent) 
	{
		
	}
	
	private void toggle(KeyEvent keyevent, boolean pressed)
	{
		if (keyevent.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
	}
}
