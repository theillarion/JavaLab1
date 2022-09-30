public class Circle implements IShape
{
	protected Point2D p;
	protected double r;

	public Circle(Point2D p, double r)
	{
		this.p = new Point2D(p);
		this.r = r;
	}

	public Point2D getP()
	{
		return p;
	}

	public void setP(Point2D p)
	{
		this.p = new Point2D(p);
	}

	public double getR()
	{
		return r;
	}

	public void setR(double r)
	{
		this.r = r;
	}

	@Override
	public Circle shift(Point2D a)
	{
		p.add(a);
		return this;
	}

	@Override
	public Circle rot(double phi)
	{
		p.rot(phi);
		return this;
	}

	@Override
	public Circle symAxis(int i)
	{
		p.symAxis(i);
		return this;
	}

	@Override
	public double square()
	{
		return Math.PI * Math.pow(r, 2);
	}

	@Override
	public double length()
	{
		return 2 * Math.PI * r;
	}

	@Override
	public boolean cross(IShape i)
	{
		return true;
	}
}
