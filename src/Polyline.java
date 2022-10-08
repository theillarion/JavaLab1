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

	@Override
	public double length()
	{
		double result = 0;
		for (int i = 0; i + 1 < n; i++)
			result += Point2D.lengthTwoPoint(p[i], p[i + 1]);
		return result;
	}

	@Override
	public boolean cross(IShape i) throws IllegalArgumentException
	{
		if (i instanceof Segment segment)
			return segment.cross(this);
		else if (i instanceof Polyline polyline)
		{
			for (int j = 0; j + 1 < polyline.getN(); j++)
				if ((new Segment(polyline.getP(j), polyline.getP(j + 1))).cross(this))
					return true;
			return false;
		}
		else if (i instanceof NGon ngon)
		{
			for (int j = 0; j + 1 < ngon.getN(); j++)
				if ((new Segment(ngon.getP(j), ngon.getP(j + 1))).cross(this))
					return true;
			return (new Segment(ngon.getP(ngon.getN() - 1), ngon.getP(0))).cross(this);
		}
		else if (i instanceof Circle circle)
		{
			for (int j = 0; j + 1 < getN(); j++)
				if ((new Segment(getP(j), getP(j + 1))).cross(circle))
					return true;
			return false;
		}
		throw new IllegalArgumentException("Shape is not found");
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++)
			str.append(p[i].toString()).append(" ");
		return String.format("Figure: Polyline; Points: {%s}", str);
	}
}
