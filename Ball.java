import java.awt.Color;
import java.awt.Graphics;

public class Ball 
{
	private Color color = Color.CYAN;
	private int radius = 20;
	
	private int x = 300;
	private int y = 300;
	private double dx = 0;
	private double dy = 0;
	private double gameDy = .10;
	
	private double gravity = 15;
	private double xFriction = .9;
	private double energyLoss = .8;
	private double dt = .2;
	
	private int healthTotal = 100;
	private int healthActual = 99; 
	static private int shot = 0;

	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Ball(int radius, Color c)
	{
		x = 400;
		y = 400;
	}
	
	public Ball(int x, int y, double dx, double dy)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public Ball(int x, int y, double dx, double dy, Color color)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.color = color;
	}
	
	/*public int getHealthTotal() {
		return healthTotal;
	}

	public void setHealthTotal(int healthTotal) {
		this.healthTotal = healthTotal;
	}

	public int getHealthActual() {
		return healthActual;
	}

	public void setHealthActual(int healthActual) {
		this.healthActual = healthActual;
	}
	*/
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	public int getRadius() 
	{
		return radius;
	}

	public void setRadius(int radius) 
	{
		this.radius = radius;
	}

	public double getDx() 
	{
		return dx;
	}

	public void setDx(double dx) 
	{
		this.dx = dx;
	}

	public double getDy() 
	{
		return dy;
	}

	public void setDy(double dy) 
	{
		this.dy = dy;
	}
	
	public double getGameDy() 
	{
		return gameDy;
	}

	public void setGameDy(double gameDy) 
	{
		this.gameDy = gameDy;
	}

	public double getGravity() 
	{
		return gravity;
	}

	public void setGravity(double gravity) 
	{
		this.gravity = gravity;
	}

	public double getxFriction() 
	{
		return xFriction;
	}

	public void setxFriction(double xFriction) 
	{
		this.xFriction = xFriction;
	}

	public double getEnergyLoss() 
	{
		return energyLoss;
	}

	public void setEnergyLoss(double energyLoss) 
	{
		this.energyLoss = energyLoss;
	}

	public double getDt() 
	{
		return dt;
	}

	public void setDt(double dt) 
	{
		this.dt = dt;
	}

	public void moveRight()
	{
		System.out.println("Moving Right");
			dx += 1;
	}
	public void moveLeft()
	{
		System.out.println("Moving Left");
			dx -= 1;
	}
	
	public void jump()
	{ 
		
		dy -= 50;
	}
	
	public void shoot()
	{
	    shot = 20;
	}
	
	public void update(StartingPoint sp)
	{
		//check to see if it went too far right
		if((x + dx) > sp.getWidth() - radius - 1)
		{
			x = sp.getWidth() - radius - 1;
			dx = - dx;
		}
		//check to see if it went to far left
		else if((x+ dx) < 0 + radius)
		{
			x = 0 + radius;
			dx = -dx;
		}
		//normal
		else 
		{
			x += dx;
		}
		
		//ball hit ceiling
		if (y <= 0)
		{
			y = 0;
			dy = - dy;
			healthActual -= 10;
		}
		//if ball is rolling, apply friction
		if(y == sp.getHeight() - radius - 1)
		{
			dx*= xFriction;
			if (Math.abs(dx) < .2)
			{
				dx = 0;
			}
		}
		//ball hits ground
		if (y  > sp.getHeight() - radius - 1)
		{
			y = sp.getHeight() - radius - 1;
			dy *= energyLoss;
			dy = -dy;
			healthActual += 100;
		}
		else if (Math.abs(dy) < .2 && y <40)
		{
			dy = 0;
		}
		else 
		{ 
			//velocity formula
			dy += gravity*dt;
			//position formula
			y += (dy*dt) + (.5*gravity*(dt*dt));
		}
	}
	public Color getBallColor() 
	{
		return color;
	}
	public void paint(Graphics g)
	{
		g.setColor(getBallColor());
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		/*if(shot > 0)
		{
			g.setColor(Color.blue);
			g.fillRect(x, y, 10, y);
			shot -= 1;
			g.create(x, y, width, height)
		}*/
		
		/*
		g.setColor(Color.red);
		g.fillRect(x+3, y + 3, healthTotal, 20);
		g.setColor(Color.green);
		g.fillRect(x + 3, y + 3, healthActual/healthTotal, 20);
		*/
	}
	
}
