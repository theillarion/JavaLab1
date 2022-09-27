public class Point3D extends Point
{
	public Point3D()
	{
		super(3);
		dim = super.getDim();
		x = new double[] {0, 0, 0};
	}

	public Point3D(double[] x)
	{
		super(3);
		dim = super.getDim();
		this.x = new double[dim];
		System.arraycopy(x, 0, this.x, 0, dim);
	}

	static public Point3D cross_prod(Point3D p1, Point3D p2)
	{
		return new Point3D(new double[]
			{
				p1.x[1] * p2.x[2] - p2.x[1] * p1.x[2],
				p2.x[0] * p1.x[2] - p1.x[0] * p2.x[2],
				p1.x[0] * p2.x[1] - p2.x[0] * p1.x[1]
			});
	}

	public Point3D cross_prod(Point3D p)
	{
		setX(new double[]
			{
				x[1] * p.x[2] - p.x[1] * x[2],
				p.x[0] * x[2] - x[0] * p.x[2],
				x[0] * p.x[1] - p.x[0] * x[1]
			});
		return this;
	}

	static public double mix_prod(Point3D p1, Point3D p2, Point3D p3)
	{
		return	p1.x[0] * (p2.x[1] * p3.x[2] - p3.x[1] * p2.x[2]) +
				p1.x[1] * (p3.x[0] * p2.x[2] - p2.x[0] * p3.x[2]) +
				p1.x[2] * (p2.x[0] * p3.x[1] - p3.x[0] * p2.x[1]);
	}

	public double mix_prod(Point3D p1, Point3D p2)
	{
		return	x[0] * (p1.x[1] * p2.x[2] - p2.x[1] * p1.x[2]) +
				x[1] * (p2.x[0] * p1.x[2] - p1.x[0] * p2.x[2]) +
				x[2] * (p1.x[0] * p2.x[1] - p2.x[0] * p1.x[1]);
	}
}
