public class Point2D extends Point
{
	public Point2D()
	{
		super(2);
	}

	public Point2D(Point2D p)
	{
		super(p);
	}

	public Point2D(double[] x)
	{
		super(2, x);
	}

	static public Point2D rot(Point2D p, double phi)
	{
		double new_X = p.x[0] * Math.cos(phi) + p.x[1] * Math.sin(phi);
		double new_Y = -p.x[0] * Math.sin(phi)+ p.x[1] * Math.cos(phi);
		return new Point2D(new double[] {new_X, new_Y});
	}

	public Point2D rot(double phi)
	{
		double X = x[0];
		double Y = x[1];
		x[0] = X * Math.cos(phi) + Y * Math.sin(phi);
		x[1] = -X * Math.sin(phi)+ Y * Math.cos(phi);
		return this;
	}
}
