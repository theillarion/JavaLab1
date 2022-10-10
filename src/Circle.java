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
		return this.p;
	}

	public void setP(Point2D p)
	{
		this.p = new Point2D(p);
	}

	public double getR()
	{
		return this.r;
	}

	public void setR(double r)
	{
		this.r = r;
	}

	@Override
	public Circle shift(Point2D a)
	{
		this.p.add(a);
		return this;
	}

	@Override
	public Circle rot(double phi)
	{
		this.p.rot(phi);
		return this;
	}

	@Override
	public Circle symAxis(int i)
	{
		this.p.symAxis(i);
		return this;
	}

	@Override
	public double square()
	{
		return Math.PI * Math.pow(this.r, 2);
	}

	@Override
	public double length()
	{
		return 2 * Math.PI * this.r;
	}

	@Override
	public boolean cross(IShape i)
	{
		if (i instanceof Circle circle)
		{
			double d = Point2D.lengthTwoPoint(this.getP(), circle.getP());
			if (d > this.r + circle.r || d < Math.abs(this.r - circle.r))
				return false;
			return true;
		}
		return i.cross(this);
	}

	@Override
	public String toString()
	{
		return String.format("Figure: Circle; Center: %s; R: %f", this.p, this.r);
	}
}
