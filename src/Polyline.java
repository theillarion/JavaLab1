import java.util.Arrays;

public class Polyline extends OpenFigure implements IPolyPoint
{
	protected int n;
	protected Point2D[] p;

	Polyline(Point2D[] p) throws IllegalArgumentException
	{
		setP(p);
	}

	public int getN()
	{
		return n;
	}

	public Point2D[] getP()
	{
		return p;
	}

	@Override
	public Point2D getP(int i)
	{
		if (i < 0 || i >= n)
			throw new ArrayIndexOutOfBoundsException();
		return p[i];
	}

	public void setP(Point2D[] p)
	{
		n = p.length;
		this.p = new Point2D[n];
		System.arraycopy(p, 0, this.p, 0, n);
	}

	@Override
	public void setP(Point2D p, int i)
	{
		if (i < 0 || i >= n)
			throw new ArrayIndexOutOfBoundsException();
		this.p[i] = p;
	}

	@Override
	public Polyline shift(Point2D a)
	{
		for (var elem : p)
			elem.add(a);
		return this;
	}

	@Override
	public Polyline rot(double phi)
	{
		for (var elem : p)
			elem.rot(phi);
		return this;
	}

	@Override
	public Polyline symAxis(int i)
	{
		for (var elem : p)
			elem.symAxis(i);
		return this;
	}

	static public double lengthTwoPoint(Point2D p1, Point2D p2)
	{
		return Math.sqrt(Math.pow(p2.x[0] - p1.x[0], 2)
			+ Math.pow(p2.x[1] - p1.x[1], 2));
	}

	@Override
	public double length()
	{
		double result = 0;
		for (int i = 0; i + 1 < n; i++)
			result += lengthTwoPoint(p[i], p[i + 1]);
		return result;
	}

	@Override
	public boolean cross(IShape i)
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "Object is polyline";
	}
}
