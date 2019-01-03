import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener
{
	//actually 60 FPS
	private int tSleep = 17;
	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p;
	
	
	public void init()
	{
		setFocusable(true);
		setSize(800,600);
		addKeyListener(this);
	}
	
	public void okay()
	{
		Thread thread2 = new Thread(new Runnable()
			{
				public void run()
				{
			
				}
			});
	}
	
	public void start()
	{
		b = new Ball(0, 300, 0, 23, Color.DARK_GRAY);
		p = new Platform();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() 
	{
		while(true)
		{	
			b.update(this);
			p.update(this, b);
			repaint();
			try 
			{
				Thread.sleep(tSleep);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void stop()
	{
		
	}
	
	public void destroy()
	{	
	}
	
	public void update(Graphics g)
	{
		if (i == null)
		{
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0, this.getSize().width, this.getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	public void paint(Graphics g)
	{
		b.paint(g);
		p.paint(g);
		
		
		Random r = new Random();
		for (int i=0; i < 1000; i++) 
		{
			Color randomColor = new Color(r.nextInt(1) + 100, r.nextInt(100) + 20, r.nextInt(100) + 150);
			g.setColor(randomColor);
			int x = Math.abs(r.nextInt() % WIDTH);
			int y = Math.abs(r.nextInt() % HEIGHT);
			int x1 = Math.abs(r.nextInt() % WIDTH);
			int y1 = Math.abs(r.nextInt() % HEIGHT);
			g.drawRect(x, y, x%20, x%20);
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println(e.getKeyChar());
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				b.moveLeft();
				System.out.print("Moving: ");
				break;
			case KeyEvent.VK_RIGHT:
				b.moveRight();
				System.out.print("Moving: ");
				break;
			case KeyEvent.VK_SPACE:
				b.jump();
				System.out.println("Jumped!");
				break;
			case KeyEvent.VK_DOWN:
				b.shoot();
				System.out.println("Shot!");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

	}


	

}
