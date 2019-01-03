import java.awt.Color;
import java.awt.Graphics;

public class Platform 
{
	int dx;
	int dy;
	int x, y, width, height;
	
	public Platform()
	{
		//dx = -2;
		dy = 1;
		x = 50;
		y = 200; 
		width = 300;
		height = 40;
	}
	
	public void update (StartingPoint sp, Ball b)
	{
		//x += dx;
		//y += dy;
		checkForCollision(b);
	}
	
	public void checkForCollision(Ball b)
	{
		int ballX = b.getX();
		int ballY = b.getY();
		int ballR = b.getRadius();
		
		int a = x - ballX;
		int bb = y - ballY;
		int collide = ballR + ballX;
		double c = Math.sqrt((a*a) + (bb*bb));
		
		/*if (c < collide)
		{
			performAction();
			x = 0;
			y = 0;
		}
		*/
		
		if (ballY + b.getRadius() > y && ballY - b.getRadius() < y + height)
		{
			if(ballX > x && ballX < x + width)
			{
				double newDY = b.getDy() * -1 * b.getEnergyLoss();
				b.setDy(newDY);
			}
		}
		
	}
	
	private void performAction() 
	{
		
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
	
}
